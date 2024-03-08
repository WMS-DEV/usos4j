package pl.wmsdev.usos4j.client;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class UsosUserAPIDefinition extends UsosServerAPIDefinition {
    protected final UsosAccessToken accessToken;

    public UsosUserAPIDefinition(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory,
                                 UsosAccessToken accessToken) {
        super(oAuthService, requestFactory);
        this.accessToken = accessToken;
    }

    protected <T> T requestWithAccessToken(OAuthRequest request, Class<T> responseClazz) {
        oAuthService.signRequest(accessToken.toFrameworkToken(), request);
        return request(request, responseClazz);
    }

    protected String requestWithAccessToken(OAuthRequest request) {
        oAuthService.signRequest(accessToken.toFrameworkToken(), request);
        return request(request);
    }
}
