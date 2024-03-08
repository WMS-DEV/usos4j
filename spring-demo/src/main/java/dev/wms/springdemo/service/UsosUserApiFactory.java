package dev.wms.springdemo.service;

import dev.wms.usos4j.client.Usos;
import dev.wms.usos4j.client.UsosUserAPI;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsosUserApiFactory {

    private final Usos usos;

    public UsosUserAPI create(UsosAccessToken accessToken) {
        return usos.getUserApi(accessToken);
    }

}