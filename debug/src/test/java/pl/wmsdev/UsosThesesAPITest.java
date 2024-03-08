package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.theses.*;

public class UsosThesesAPITest extends UsosAPIBaseTest {

    @Test
    void search() {
        log(userApi.theses().search(UsosThesesSearchParams.builder(UsosLanguage.PL).build()));
    }

    @Test
    void thesis() {
        log(userApi.theses().thesis(UsosThesisParams.builder("15").build()));
    }

    @Test
    void theses() {
        log(userApi.theses().theses(UsosThesesParams.builder("15", "16").build()));
    }

    @Test
    void user() {
        log(userApi.theses().user(UsosThesesUserParams.builder("603960").build()));
    }

    @Test
    void users() {
        log(userApi.theses().users(UsosThesesUsersParams.builder("603960").build()));
    }

}