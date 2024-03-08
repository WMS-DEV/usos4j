package pl.wmsdev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class APIRefTest extends UsosAPIBaseTest {

    @Test
    void method() {
        serverApi.apiRef().method("services/apiref/module");
    }

    @Test
    void methodIndex() {
        assertFalse(serverApi.apiRef().methodIndex().isEmpty());
    }

    @Test
    void module() {
        serverApi.apiRef().module("services/crstests");
    }

    @Test
    void scopes() {
        assertFalse(serverApi.apiRef().scopes().isEmpty());
    }


}
