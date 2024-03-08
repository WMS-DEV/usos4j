package dev.wms.usos4j.client;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.model.auth.UsosScope;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScribeBasedUsosAPI extends DefaultApi10a {

    private final String BASE_URL;
    private final Collection<UsosScope> scopes;

    @Override
    public String getRequestTokenEndpoint() {
        return BASE_URL + "services/oauth/request_token?scopes=" + generateScopes(scopes);
    }

    @Override
    public String getAccessTokenEndpoint() {
        return BASE_URL + "services/oauth/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return BASE_URL + "services/oauth/authorize";
    }


    @Override
    public OAuth10aService createService(String apiKey, String apiSecret, String callback, String scope, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        return new UsosOAuthService(this, apiKey, apiSecret, callback, scope, debugStream, userAgent, httpClientConfig, httpClient);
    }

    private String generateScopes(Collection<UsosScope> scopes) {
        return scopes.stream()
                .map(UsosScope::getScope)
                .collect(Collectors.joining("|"));
    }

}
