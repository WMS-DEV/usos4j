package dev.wms.springsecuritydemo.auth;

import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.auth.UsosRequestToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UsosAuthService usosAuthService;

    @GetMapping( "/request-token")
    public RequestTokenResponse requestToken() {
        UsosRequestToken requestToken = usosAuthService.getRequestToken();
        String authorizationUrl = usosAuthService.getAuthorizationUrl(requestToken);
        return new RequestTokenResponse(requestToken.getToken(),
                requestToken.getTokenSecret(),
                authorizationUrl);
    }

    @PostMapping("/access-token")
    public ResponseEntity<AuthResponse> accessToken(@RequestBody AccessRequest request) {

        UsosRequestToken usosRequestToken = new UsosRequestToken(request.requestToken(), request.tokenSecret());
        UsosAccessToken accessToken = usosAuthService.getAccessToken(usosRequestToken, request.verifier());

        return ResponseEntity.ok(authService.authenticate(new AuthRequest(accessToken.getToken(), accessToken.getTokenSecret())));
    }
}
