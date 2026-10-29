package pl.wmsdev.usos4j.unit;

import com.github.scribejava.core.httpclient.jdk.JDKHttpClient;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.client.UsosHttpConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class UsosTransportConfigTest {

    private HttpServer server;
    private ExecutorService executor;
    private String baseUrl;
    private final CountDownLatch release = new CountDownLatch(1);
    private final AtomicInteger targetRequests = new AtomicInteger();
    private final AtomicReference<String> userAgent = new AtomicReference<>();
    private final AtomicReference<String> requestUri = new AtomicReference<>();

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        executor = Executors.newCachedThreadPool();
        server.setExecutor(executor);
        server.createContext("/", exchange -> {
            var path = exchange.getRequestURI().getPath();
            userAgent.set(exchange.getRequestHeaders().getFirst("User-Agent"));
            requestUri.set(exchange.getRequestURI().toString());
            if (path.contains("slow") || path.contains("request_token")) {
                try {
                    release.await(3, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (path.endsWith("target")) targetRequests.incrementAndGet();
            int status = path.endsWith("redirect") ? 302 : 200;
            if (status == 302) exchange.getResponseHeaders().set("Location", "/services/target");
            var body = "{\"id\":7}".getBytes(StandardCharsets.UTF_8);
            try {
                exchange.sendResponseHeaders(status, body.length);
                try (var output = exchange.getResponseBody()) {
                    output.write(body);
                }
            } finally {
                exchange.close();
            }
        });
        server.start();
        baseUrl = "http://127.0.0.1:" + server.getAddress().getPort() + "/";
    }

    @AfterEach
    void stopServer() {
        release.countDown();
        if (server != null) server.stop(0);
        if (executor != null) executor.shutdownNow();
    }

    @Test
    void appliesReadTimeoutToApiRequests() throws IOException {
        var usos = builder().httpConfig(UsosHttpConfig.builder().readTimeout(Duration.ofMillis(100)).build()).build();
        try (var service = usos.getOAuthService()) {
            assertTimeoutPreemptively(Duration.ofSeconds(5), () ->
                    assertThrows(SocketTimeoutException.class, () -> usos.getServerApi().generic().request("services/slow", Map.class)));
        }
    }

    @Test
    void appliesReadTimeoutToOAuthTokenExchange() throws IOException {
        var usos = builder().httpConfig(UsosHttpConfig.builder().readTimeout(Duration.ofMillis(100)).build()).build();
        try (var service = usos.getOAuthService()) {
            assertTimeoutPreemptively(Duration.ofSeconds(5), () -> assertThrows(SocketTimeoutException.class, usos::getRequestToken));
        }
    }

    @Test
    void configuresProxyAndUserAgent() throws IOException {
        var proxy = new Proxy(Proxy.Type.HTTP, server.getAddress());
        var usos = builder().baseUrl("http://unreachable.invalid/")
                .httpConfig(UsosHttpConfig.builder().proxy(proxy).connectTimeout(Duration.ofSeconds(1))
                        .readTimeout(Duration.ofSeconds(1)).userAgent("fixture-client/1.0").build()).build();
        try (var service = usos.getOAuthService()) {
            assertEquals(7, usos.getServerApi().generic().request("services/test", Map.class).get("id"));
            assertTrue(requestUri.get().startsWith("http://unreachable.invalid/services/test"));
            assertEquals("fixture-client/1.0", userAgent.get());
        }
    }

    @Test
    void allowsDisablingAndEnablingRedirects() throws IOException {
        for (boolean follow : new boolean[]{false, true}) {
            var usos = builder().httpConfig(UsosHttpConfig.builder().followRedirects(follow).build()).build();
            try (var service = usos.getOAuthService()) {
                try (var response = service.execute(new OAuthRequest(Verb.GET, baseUrl + "services/redirect"))) {
                    assertEquals(follow ? 200 : 302, response.getCode());
                    assertEquals(follow ? 1 : 0, targetRequests.get());
                } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
                    throw new AssertionError(e);
                }
            }
        }
    }

    @Test
    void preservesOldConstructorBuilderAndFactory() throws IOException {
        var legacy = new Usos(baseUrl, "", "key", "secret", false, Set.of());
        var defaults = builder().build();
        var factory = Usos.serverOnly(baseUrl, "key", "secret");
        var emptyConfig = builder().httpConfig(UsosHttpConfig.builder().build()).build();
        for (var usos : new Usos[]{legacy, defaults, factory, emptyConfig}) {
            try (var service = usos.getOAuthService()) {
                assertEquals(7, usos.getServerApi().generic().request("services/test", Map.class).get("id"));
            }
        }
    }

    @Test
    void usesAndClosesInjectedTransport() throws IOException {
        var calls = new AtomicInteger();
        var closes = new AtomicInteger();
        var client = new JDKHttpClient() {
            @Override
            public Response execute(String agent, Map<String, String> headers, Verb verb, String url, byte[] body) {
                calls.incrementAndGet();
                return new Response(200, "OK", Map.of(), "{\"id\":42}");
            }

            @Override
            public void close() {
                closes.incrementAndGet();
            }
        };
        var usos = builder().httpClient(client).build();
        try (var service = usos.getOAuthService()) {
            assertEquals(42, usos.getServerApi().generic().request("services/test", Map.class).get("id"));
            assertEquals(1, calls.get());
            assertEquals(0, targetRequests.get());
        }
        assertEquals(1, closes.get());
    }

    @Test
    void rejectsConflictingConfiguration() {
        assertThrows(IllegalArgumentException.class,
                () -> builder().httpConfig(UsosHttpConfig.builder().build()).httpClient(new JDKHttpClient()).build());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0, 2147483648L, Long.MAX_VALUE})
    void rejectsInvalidTimeouts(long millis) {
        assertThrows(IllegalArgumentException.class, () -> UsosHttpConfig.builder().readTimeout(Duration.ofMillis(millis)).build());
        assertThrows(IllegalArgumentException.class, () -> UsosHttpConfig.builder().connectTimeout(Duration.ofMillis(millis)).build());
    }

    @Test
    void validatesSubMillisecondAndOverflowingTimeouts() {
        assertThrows(IllegalArgumentException.class, () -> UsosHttpConfig.builder().readTimeout(Duration.ofNanos(1)).build());
        assertThrows(IllegalArgumentException.class, () -> UsosHttpConfig.builder().readTimeout(Duration.ofSeconds(Long.MAX_VALUE)).build());
        assertDoesNotThrow(() -> UsosHttpConfig.builder().connectTimeout(Duration.ofMillis(1))
                .readTimeout(Duration.ofMillis(Integer.MAX_VALUE)).build());
    }

    private Usos.UsosBuilder builder() {
        return Usos.builder().baseUrl(baseUrl).consumerKey("key").consumerSecret("secret");
    }
}
