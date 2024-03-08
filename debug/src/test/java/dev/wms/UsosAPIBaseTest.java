package dev.wms;

import dev.wms.usos4j.client.UsosServerAPI;
import dev.wms.usos4j.client.UsosUserAPI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(HeadlessUsosProviderExtension.class)
@Slf4j
public class UsosAPIBaseTest {
    protected final UsosServerAPI serverApi = HeadlessUsosHolder.serverApi();
    protected final UsosUserAPI userApi = HeadlessUsosHolder.userApi();

    protected final void log(Object o) {
        log.info(o.toString());
    }
}
