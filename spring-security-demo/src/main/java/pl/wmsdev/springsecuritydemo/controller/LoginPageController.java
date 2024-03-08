package pl.wmsdev.springsecuritydemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping()
public class LoginPageController {

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

}
