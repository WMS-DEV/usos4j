package dev.wms.springdemo.service.userapi;

import dev.wms.usos4j.client.Usos;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.auth.UsosRequestToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsosAuthService {

    private final Usos usos;

    public String getAuthorizationUrl(UsosRequestToken requestToken) {
        return usos.getAuthorizationUrl(requestToken);
    }
    
    public UsosRequestToken getRequestToken() {
        return usos.getRequestToken();
    }
    
    public UsosAccessToken getAccessToken(UsosRequestToken requestToken, String verifier) {
        return usos.getAccessToken(requestToken, verifier);
    }

}
