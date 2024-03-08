package dev.wms;

import dev.wms.usos4j.model.apiref.UsosApiRefModule;
import org.junit.jupiter.api.Test;

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
