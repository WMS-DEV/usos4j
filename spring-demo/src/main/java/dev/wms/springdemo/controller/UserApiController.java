package dev.wms.springdemo.controller;

import dev.wms.springdemo.service.userapi.UsosAuthService;
import dev.wms.springdemo.service.userapi.UsosUserService;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.auth.UsosRequestToken;
import dev.wms.usos4j.model.user.UsosUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userapi")
public class UserApiController {

    private final UsosAuthService authService;
    private final UsosUserService userService;

    @GetMapping("/user")
    public UsosUser user(@RequestParam String accessToken, @RequestParam String tokenSecret) {
        return userService.getUser(new UsosAccessToken(accessToken, tokenSecret));
    }

    @GetMapping(value = "/request-token")
    public Map<String, String> requestToken() {
        UsosRequestToken usosRequestToken = authService.getRequestToken();
        String authorizationUrl = authService.getAuthorizationUrl(usosRequestToken);
        return Map.of(
                "requestToken", usosRequestToken.getToken(),
                "tokenSecret", usosRequestToken.getTokenSecret(),
                "authorizationUrl", authorizationUrl
        );
    }

    @GetMapping(value = "/access-token", params = { "requestToken", "tokenSecret", "verifier" })
    public Map<String, String> accessToken(@RequestParam String requestToken, @RequestParam String tokenSecret, @RequestParam String verifier) {
        UsosAccessToken accessToken = authService.getAccessToken(new UsosRequestToken(requestToken, tokenSecret), verifier);
        return Map.of(
                "accessToken", accessToken.getToken(),
                "tokenSecret", accessToken.getTokenSecret()
        );
    }
}