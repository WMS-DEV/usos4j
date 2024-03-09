package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.plctests.UsosPlacementTestParams;
import pl.wmsdev.usos4j.model.plctests.UsosPlacementTestsResultParams;

public class UsosPlacementTestsAPITest extends UsosAPIBaseTest {

    @Test
    void testsIndex() {
        log(serverApi.plcTests().testsIndex());
    }

    @Test
    void test() {
        log(serverApi.plcTests().test(UsosPlacementTestParams.builder("1").build()));
    }

    @Test
    void testResult() {
        log(userApi.plcTests().testResult(UsosPlacementTestsResultParams.builder("1").build()));
    }

    @Test
    void user() {
        log(userApi.plcTests().user());
    }
}
