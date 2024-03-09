package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.apiref.UsosApiRefModule;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.List;
import java.util.Map;

public class UsosUserAPITest extends UsosAPIBaseTest {

    @Test
    void genericServerApi() {
        log(userApi.generic()
                .request("services/apiref/module", Map.of("name", List.of("services/crstests")), UsosApiRefModule.class));
    }

    @Test
    void genericServerApiWithParams() {
        log(userApi.generic()
                .request("services/apiref/method", Map.of("name", List.of("services/apiref/module")), UsosApiRefModule.class,
                        "name"));
    }

    @Test
    void genericUserApi() {
        log(userApi.generic()
                .request("services/users/user", Map.of("fields", List.of("first_name", "last_name")),
                UsosUser.class));
    }

    @Test
    void genericUserApiWithFields() {
        log(userApi.generic()
                .request("services/users/user", UsosUser.class, "first_name", "last_name"));
    }

}
