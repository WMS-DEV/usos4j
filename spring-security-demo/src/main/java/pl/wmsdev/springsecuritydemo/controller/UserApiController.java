package pl.wmsdev.springsecuritydemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wmsdev.springsecuritydemo.config.JwtService;
import pl.wmsdev.springsecuritydemo.service.userapi.UsosUserService;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.user.UsosUser;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userapi")
public class UserApiController {

    private final UsosUserService userService;
    private final JwtService jwtService;

    @GetMapping("/user")
    public UsosUser user() {
        UsosAccessToken accessToken = jwtService.getAccessToken();
        return userService.getUser(accessToken);
    }

}