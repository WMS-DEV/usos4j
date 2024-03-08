package pl.wmsdev.springdemo.service.userapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wmsdev.springdemo.service.UsosUserApiFactory;
import pl.wmsdev.usos4j.client.UsosUserAPI;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.user.UsosUser;

@RequiredArgsConstructor
@Component
public class UsosUserService {

    private final UsosUserApiFactory userApiFactory;

    public UsosUser getUser(UsosAccessToken accessToken) {
        UsosUserAPI userApi = userApiFactory.create(accessToken);
        return userApi.users().user();
    }

}
