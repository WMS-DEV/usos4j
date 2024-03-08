package dev.wms.springdemo.service.userapi;

import dev.wms.springdemo.service.UsosUserApiFactory;
import dev.wms.usos4j.client.UsosUserAPI;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.user.UsosUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsosUserService {

    private final UsosUserApiFactory userApiFactory;

    public UsosUser getUser(UsosAccessToken accessToken) {
        UsosUserAPI userApi = userApiFactory.create(accessToken);
        return userApi.users().user();
    }

}
