package pl.wmsdev.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.client.UsosUserAPI;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;

@RequiredArgsConstructor
@Component
public class UsosUserApiFactory {

    private final Usos usos;

    public UsosUserAPI create(UsosAccessToken accessToken) {
        return usos.getUserApi(accessToken);
    }

}