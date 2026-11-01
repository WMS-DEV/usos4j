package pl.wmsdev.usos4jheadless;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.auth.UsosRequestToken;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeadlessUsosProviderTest {

    @BeforeEach
    void setUp() {
        HeadlessUsosProvider.clearAccessTokenCache();
    }

    @Test
    void getCachesAccessTokenAndSkipsOauthFlowForSameLogin() {
        FakeUsos fakeUsos = new FakeUsos(false);
        AtomicInteger authenticationCalls = new AtomicInteger();
        HeadlessUsosProvider provider = new HeadlessUsosProvider(
                (authUrl, login, password) -> {
                    authenticationCalls.incrementAndGet();
                    return "verifier";
                },
                (baseUrl, consumerKey, consumerSecret) -> fakeUsos
        );

        HeadlessUsos first = provider.get("https://base", "key", "secret", "user", "pass");
        HeadlessUsos second = provider.get("https://base", "key", "secret", "user", "pass");

        assertSame(first.accessToken(), second.accessToken());
        assertEquals(1, authenticationCalls.get());
        assertEquals(1, fakeUsos.requestTokenCalls);
        assertEquals(1, fakeUsos.authorizationUrlCalls);
        assertEquals(1, fakeUsos.accessTokenCalls);
    }

    @Test
    void getDoesNotCacheWhenAccessTokenExchangeFails() {
        FakeUsos fakeUsos = new FakeUsos(true);
        AtomicInteger authenticationCalls = new AtomicInteger();
        HeadlessUsosProvider provider = new HeadlessUsosProvider(
                (authUrl, login, password) -> {
                    authenticationCalls.incrementAndGet();
                    return "verifier";
                },
                (baseUrl, consumerKey, consumerSecret) -> fakeUsos
        );

        assertThrows(IllegalStateException.class,
                () -> provider.get("https://base", "key", "secret", "user", "pass"));
        provider.get("https://base", "key", "secret", "user", "pass");

        assertEquals(2, authenticationCalls.get());
        assertEquals(2, fakeUsos.requestTokenCalls);
        assertEquals(2, fakeUsos.authorizationUrlCalls);
        assertEquals(2, fakeUsos.accessTokenCalls);
    }

    @Test
    void getCachesTokensSeparatelyForDifferentLogins() {
        FakeUsos fakeUsos = new FakeUsos(false);
        AtomicInteger authenticationCalls = new AtomicInteger();
        HeadlessUsosProvider provider = new HeadlessUsosProvider(
                (authUrl, login, password) -> {
                    authenticationCalls.incrementAndGet();
                    return "verifier";
                },
                (baseUrl, consumerKey, consumerSecret) -> fakeUsos
        );

        HeadlessUsos first = provider.get("https://base", "key", "secret", "user-a", "pass");
        HeadlessUsos second = provider.get("https://base", "key", "secret", "user-b", "pass");

        assertEquals("access-1", first.accessToken().getToken());
        assertEquals("access-2", second.accessToken().getToken());
        assertEquals(2, authenticationCalls.get());
        assertEquals(2, fakeUsos.requestTokenCalls);
        assertEquals(2, fakeUsos.authorizationUrlCalls);
        assertEquals(2, fakeUsos.accessTokenCalls);
    }

    @Test
    void getCachesTokensSeparatelyForDifferentBaseUrlsWithSameLogin() {
        FakeUsos fakeUsos = new FakeUsos(false);
        AtomicInteger authenticationCalls = new AtomicInteger();
        HeadlessUsosProvider provider = new HeadlessUsosProvider(
                (authUrl, login, password) -> {
                    authenticationCalls.incrementAndGet();
                    return "verifier";
                },
                (baseUrl, consumerKey, consumerSecret) -> fakeUsos
        );

        HeadlessUsos first = provider.get("https://base-a", "key", "secret", "user", "pass");
        HeadlessUsos second = provider.get("https://base-b", "key", "secret", "user", "pass");

        assertEquals("access-1", first.accessToken().getToken());
        assertEquals("access-2", second.accessToken().getToken());
        assertEquals(2, authenticationCalls.get());
        assertEquals(2, fakeUsos.accessTokenCalls);
    }

    @Test
    void getCachesTokensSeparatelyForDifferentConsumerKeysWithSameLogin() {
        FakeUsos fakeUsos = new FakeUsos(false);
        AtomicInteger authenticationCalls = new AtomicInteger();
        HeadlessUsosProvider provider = new HeadlessUsosProvider(
                (authUrl, login, password) -> {
                    authenticationCalls.incrementAndGet();
                    return "verifier";
                },
                (baseUrl, consumerKey, consumerSecret) -> fakeUsos
        );

        HeadlessUsos first = provider.get("https://base", "key-a", "secret", "user", "pass");
        HeadlessUsos second = provider.get("https://base", "key-b", "secret", "user", "pass");

        assertEquals("access-1", first.accessToken().getToken());
        assertEquals("access-2", second.accessToken().getToken());
        assertEquals(2, authenticationCalls.get());
        assertEquals(2, fakeUsos.accessTokenCalls);
    }

    private static final class FakeUsos extends Usos {
        private final boolean failOnFirstAccessTokenExchange;
        private int requestTokenCalls;
        private int authorizationUrlCalls;
        private int accessTokenCalls;

        private FakeUsos(boolean failOnFirstAccessTokenExchange) {
            super("https://apps.usos.test/", "", "consumer", "secret", false, Set.of());
            this.failOnFirstAccessTokenExchange = failOnFirstAccessTokenExchange;
        }

        @Override
        public UsosRequestToken getRequestToken() {
            requestTokenCalls++;
            return new UsosRequestToken("request-" + requestTokenCalls, "request-secret-" + requestTokenCalls);
        }

        @Override
        public String getAuthorizationUrl(UsosRequestToken requestToken) {
            authorizationUrlCalls++;
            return "https://apps.usos.test/oauth/authorize?oauth_token=" + requestToken.getToken();
        }

        @Override
        public UsosAccessToken getAccessToken(UsosRequestToken requestToken, String verifier) {
            accessTokenCalls++;
            if (failOnFirstAccessTokenExchange && accessTokenCalls == 1) {
                throw new IllegalStateException("Cannot exchange token");
            }
            return new UsosAccessToken("access-" + accessTokenCalls, "access-secret-" + accessTokenCalls);
        }
    }
}
