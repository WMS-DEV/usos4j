package pl.wmsdev.springdemo.service.userapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.auth.UsosRequestToken;

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
