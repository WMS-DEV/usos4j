package pl.wmsdev;

import org.junit.jupiter.api.Test;

public class UsosMailingAPITest extends UsosAPIBaseTest {

    @Test
    void systemStatus() {
        log(serverApi.mailing().systemStatus());
    }

}
