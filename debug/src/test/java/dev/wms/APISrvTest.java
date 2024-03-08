package dev.wms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class APISrvTest extends UsosAPIBaseTest {

    @Test
    void consumer() {
        assertFalse(serverApi.apiSrv().consumer().name().isBlank());
    }

    @Test
    void installation() {
        assertFalse(serverApi.apiSrv().installation().baseUrl().isEmpty());
    }

    @Test
    void installations() {
        assertFalse(serverApi.apiSrv().installations().isEmpty());
    }

    @Test
    void now() {
        serverApi.apiSrv().now();
    }


}
