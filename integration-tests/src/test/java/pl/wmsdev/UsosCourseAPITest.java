package pl.wmsdev;

import org.junit.jupiter.api.Test;

public class UsosCourseAPITest extends UsosAPIBaseTest {

    @Test
    void user() {
        log(userApi.courses().user());
    }

}
