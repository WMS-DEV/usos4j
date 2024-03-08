package pl.wmsdev.springsecuritydemo.auth;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wmsdev.springsecuritydemo.config.JwtService;
import pl.wmsdev.springsecuritydemo.service.userapi.UsosUserService;
import pl.wmsdev.springsecuritydemo.user.User;
import pl.wmsdev.springsecuritydemo.user.UserRepository;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UsosUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public AuthResponse authenticate(AuthRequest request) {

        String accessToken = request.getAccessToken();
        String accessTokenSecret = request.getAccessTokenSecret();
        String userId;

        try {
            userId = userService.getUser(new UsosAccessToken(accessToken, accessTokenSecret)).id().toString();
        } catch (Exception e) {
            throw new CredentialNotFoundException("Incorrect access token. Login and try again");
        }

        User user = repository.findUserByUsername(userId).orElseGet(() -> {
            User newUser = createNewUser(userId);
            repository.save(newUser);
            return newUser;
        });

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userId, userId
                )
        );

        var jwtToken = jwtService.generateToken(Map.of("accessToken", accessToken, "accessTokenSecret", accessTokenSecret),user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    private User createNewUser(String userId) {
        return User.builder()
                .username(userId)
                .password(passwordEncoder.encode(userId))
                .build();
    }
}
