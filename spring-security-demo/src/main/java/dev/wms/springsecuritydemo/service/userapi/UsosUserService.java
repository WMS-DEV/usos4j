package dev.wms.springsecuritydemo.service.userapi;

import dev.wms.springsecuritydemo.service.factory.UsosUserApiFactory;
import dev.wms.usos4j.client.UsosUserApi;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.user.UsosUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsosUserService {

    private final UsosUserApiFactory userApiFactory;

    public UsosUser getUser(UsosAccessToken accessToken) {
        UsosUserApi userApi = userApiFactory.create(accessToken);
        return userApi.users().user();
    }

}
