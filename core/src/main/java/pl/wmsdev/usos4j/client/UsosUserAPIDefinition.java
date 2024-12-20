package pl.wmsdev.usos4j.client;

import com.fasterxml.jackson.core.type.TypeReference;
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
        assertAccessTokenPresent();
        oAuthService.signRequest(accessToken.toFrameworkToken(), request);
        return request(request, responseClazz);
    }

    protected <T> T requestWithAccessToken(OAuthRequest request, TypeReference<T> typeReference) {
        assertAccessTokenPresent();
        oAuthService.signRequest(accessToken.toFrameworkToken(), request);
        return request(request, typeReference);
    }

    protected String requestWithAccessToken(OAuthRequest request) {
        assertAccessTokenPresent();
        oAuthService.signRequest(accessToken.toFrameworkToken(), request);
        return request(request);
    }

    private void assertAccessTokenPresent() {
        if(accessToken == null) {
            throw new IllegalStateException("Tried to make authenticated request, but no access token is present - " +
                    "make sure you are using User scoped API");
        }
    }

}
