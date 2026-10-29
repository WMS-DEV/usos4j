package pl.wmsdev.usos4j.unit;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.terms.UsosTerm;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.jupiter.api.Assertions.*;

class UsosLocalHttpTest {

    private HttpServer server;
    private Usos usos;
    private final AtomicReference<String> path = new AtomicReference<>();
    private final AtomicReference<String> query = new AtomicReference<>();
    private final AtomicReference<String> authorization = new AtomicReference<>();
    private final AtomicReference<String> response = new AtomicReference<>();
    private final AtomicInteger requests = new AtomicInteger();

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        response.set(UsosJsonFixtureTest.fixture("term.json"));
        server.createContext("/", exchange -> {
            requests.incrementAndGet();
            path.set(exchange.getRequestURI().getPath());
            query.set(exchange.getRequestURI().getRawQuery());
            authorization.set(exchange.getRequestHeaders().getFirst("Authorization"));
            var body = response.get().getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, body.length);
            try (var output = exchange.getResponseBody()) {
                output.write(body);
            }
        });
        server.start();
        usos = Usos.serverOnly("http://127.0.0.1:" + server.getAddress().getPort() + "/", "fixture-key", "fixture-secret");
    }

    @AfterEach
    void stopServer() throws IOException {
        if (usos != null) usos.getOAuthService().close();
        if (server != null) server.stop(0);
    }

    @Test
    void signsServerRequestsWithConsumerCredentialsAndDeserializesResponse() {
        var term = usos.getServerApi().generic().request("services/terms/term",
                Map.of("term_id", java.util.List.of("2026Z")), UsosTerm.class);
        assertEquals("/services/terms/term", path.get());
        assertEquals("term_id=2026Z", query.get());
        assertEquals(LocalDate.of(2026, 10, 1), term.startDate());
        assertSigned(false);
    }

    @Test
    void signsUserRequestsAndPreservesFieldsAndDateParameters() throws IOException {
        response.set(UsosJsonFixtureTest.fixture("timetable.json"));
        var activities = usos.getUserApi(new UsosAccessToken("fixture-token", "fixture-token-secret"))
                .timeTable().student(LocalDate.of(2026, 10, 28), 7);
        assertEquals("/services/tt/student", path.get());
        var parameters = parameters();
        assertEquals("2026-10-28", parameters.get("start"));
        assertEquals("7", parameters.get("days"));
        assertTrue(parameters.get("fields").contains("start_time"));
        assertEquals(1, activities.size());
        assertSigned(true);
    }

    @Test
    void supportsGenericUserRequestsWithSyntheticData() throws IOException {
        response.set(UsosJsonFixtureTest.fixture("user.json"));
        var user = usos.getUserApi(new UsosAccessToken("fixture-token", "fixture-token-secret"))
                .generic().request("services/users/user", UsosUser.class, "id", "first_name");
        assertEquals("Żaneta", user.firstName());
        assertEquals("id|first_name", parameters().get("fields"));
        assertSigned(true);
    }

    @Test
    void encodesReservedCharactersUnicodeAndExistingQueryParameters() {
        var value = "Żaneta & A+B / ? # =";
        usos.getServerApi().generic().request("services/test?existing=ok",
                Map.of("search", java.util.List.of(value), "fields", java.util.List.of("id", "name", "id")), UsosTerm.class);
        assertEquals("/services/test", path.get());
        assertEquals(value, parameters().get("search"));
        assertEquals("id|name", parameters().get("fields"));
        assertEquals("ok", parameters().get("existing"));
        assertSigned(false);
    }

    @Test
    void rejectsMissingAccessTokenBeforeSendingRequest() {
        assertThrows(IllegalStateException.class, () -> usos.getUserApi(null).users().user());
        assertEquals(0, requests.get());
    }

    private Map<String, String> parameters() {
        return Arrays.stream(query.get().split("&"))
                .map(parameter -> parameter.split("=", 2))
                .collect(Collectors.toMap(parts -> URLDecoder.decode(parts[0], StandardCharsets.UTF_8),
                        parts -> URLDecoder.decode(parts[1], StandardCharsets.UTF_8)));
    }

    private void assertSigned(boolean user) {
        var header = authorization.get();
        assertNotNull(header);
        assertTrue(header.startsWith("OAuth "));
        assertTrue(header.contains("oauth_consumer_key=\"fixture-key\""));
        assertTrue(header.contains("oauth_signature="));
        assertTrue(header.contains("oauth_nonce="));
        assertTrue(header.contains("oauth_timestamp="));
        assertEquals(user, header.contains("oauth_token=\"fixture-token\""));
        var oauth = Arrays.stream(header.substring("OAuth ".length()).split(",\\s*"))
                .map(parameter -> parameter.split("=", 2))
                .collect(Collectors.toMap(parts -> parts[0],
                        parts -> URLDecoder.decode(parts[1].replace("\"", ""), StandardCharsets.UTF_8)));
        var signature = oauth.remove("oauth_signature");
        var signedParameters = new TreeMap<String, String>(parameters());
        signedParameters.putAll(oauth);
        var normalized = signedParameters.entrySet().stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue())).collect(Collectors.joining("&"));
        var url = "http://127.0.0.1:" + server.getAddress().getPort() + path.get();
        var signatureBase = "GET&" + encode(url) + "&" + encode(normalized);
        var expected = assertDoesNotThrow(() -> {
            var mac = Mac.getInstance("HmacSHA1");
            var secret = "fixture-secret&" + (user ? "fixture-token-secret" : "");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA1"));
            return java.util.Base64.getEncoder().encodeToString(mac.doFinal(signatureBase.getBytes(StandardCharsets.UTF_8)));
        });
        assertEquals(expected, signature);
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }
}
