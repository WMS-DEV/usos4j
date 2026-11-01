package pl.wmsdev.usos4jheadless;

import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.auth.UsosScopes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class used to provide oAuth credentials without oauth,
 * used for integration testing with USOS API
 */
public class HeadlessUsosProvider {

    private static final Map<AccessTokenCacheKey, UsosAccessToken> cachedAccessTokens = new ConcurrentHashMap<>();

    private final OauthVerifierProvider oauthVerifierProvider;
    private final UsosFactory usosFactory;

    public HeadlessUsosProvider() {
        this(
                new HeadlessOAuthAuthenticator()::authenticate,
                (baseUrl, consumerKey, consumerSecret) -> Usos.builder()
                        .baseUrl(baseUrl)
                        .consumerKey(consumerKey)
                        .consumerSecret(consumerSecret)
                        .debug(true)
                        .scopes(UsosScopes.allStudent())
                        .build()
        );
    }

    HeadlessUsosProvider(OauthVerifierProvider oauthVerifierProvider, UsosFactory usosFactory) {
        this.oauthVerifierProvider = oauthVerifierProvider;
        this.usosFactory = usosFactory;
    }

    public HeadlessUsos get(String baseUrl, String consumerKey, String consumerSecret, String login, String password) {
        Usos usos = usosFactory.create(baseUrl, consumerKey, consumerSecret);
        var cacheKey = new AccessTokenCacheKey(baseUrl, consumerKey, login);
        UsosAccessToken cachedAccessToken = cachedAccessTokens.get(cacheKey);
        if (cachedAccessToken != null) {
            return new HeadlessUsos(cachedAccessToken, usos);
        }

        var requestToken = usos.getRequestToken();
        var authUrl = usos.getAuthorizationUrl(requestToken);
        var verifier = oauthVerifierProvider.authenticate(authUrl, login, password);
        var accessToken = usos.getAccessToken(requestToken, verifier);
        if (accessToken != null) {
            cachedAccessTokens.put(cacheKey, accessToken);
        }
        return new HeadlessUsos(accessToken, usos);
    }

    static void clearAccessTokenCache() {
        cachedAccessTokens.clear();
    }

    private record AccessTokenCacheKey(String baseUrl, String consumerKey, String login) {
    }

    @FunctionalInterface
    interface OauthVerifierProvider {
        String authenticate(String authUrl, String login, String password);
    }

    @FunctionalInterface
    interface UsosFactory {
        Usos create(String baseUrl, String consumerKey, String consumerSecret);
    }

}
