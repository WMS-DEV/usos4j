package pl.wmsdev.usos4jheadless;

import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosScopes;

/**
 * Class used to provide oAuth credentials without oauth,
 * used for integration testing with USOS API
 */
public class HeadlessUsosProvider {

    private final HeadlessOAuthAuthenticator authAuthenticator = new HeadlessOAuthAuthenticator();

    public HeadlessUsos get(String baseUrl, String consumerKey, String consumerSecret,
                     String login, String password) {
        Usos usos = Usos.builder()
                .baseUrl(baseUrl)
                .consumerKey(consumerKey)
                .consumerSecret(consumerSecret)
                .debug(true)
                .scopes(UsosScopes.allStudent())
                .build();
        var requestToken = usos.getRequestToken();
        var authUrl = usos.getAuthorizationUrl(requestToken);
        var verifier = authAuthenticator.authenticate(authUrl, login, password);
        return new HeadlessUsos(usos.getAccessToken(requestToken, verifier), usos);
    }



}
