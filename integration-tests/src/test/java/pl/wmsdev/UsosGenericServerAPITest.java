package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.apiref.UsosApiRefModule;

import java.util.List;
import java.util.Map;

public class UsosGenericServerAPITest extends UsosAPIBaseTest {

    @Test
    void genericServerApi() {
        log(serverApi.generic()
                .request("services/apiref/module", Map.of("name", List.of("services/crstests")), UsosApiRefModule.class));
    }

    @Test
    void genericServerApiWithParams() {
        log(serverApi.generic()
                .request("services/apiref/method", Map.of("name", List.of("services/apiref/module")), UsosApiRefModule.class,
                        "name"));
    }

}
