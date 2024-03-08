package dev.wms;

import org.junit.jupiter.api.Test;

public class UsosCourseAPITest extends UsosAPIBaseTest {

    @Test
    void user() {
        log(userApi.users().user());
    }

}
