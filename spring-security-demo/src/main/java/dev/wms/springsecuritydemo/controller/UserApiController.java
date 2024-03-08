package dev.wms.springsecuritydemo.controller;

import dev.wms.springsecuritydemo.config.JwtService;
import dev.wms.springsecuritydemo.service.userapi.UsosUserService;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.user.UsosUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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