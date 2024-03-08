package pl.wmsdev;

import org.junit.jupiter.api.Test;

public class UsosMailingServerAPITest extends UsosAPIBaseTest {

    @Test
    void systemStatus() {
        log(serverApi.mailing().systemStatus());
    }

}
