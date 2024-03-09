package pl.wmsdev;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.wmsdev.usos4j.client.UsosServerAPI;
import pl.wmsdev.usos4j.client.UsosUserAPI;

@ExtendWith(HeadlessUsosProviderExtension.class)
@Slf4j
public class UsosAPIBaseTest {
    protected final UsosServerAPI serverApi = HeadlessUsosHolder.serverApi();
    protected final UsosUserAPI userApi = HeadlessUsosHolder.userApi();

    protected final void log(Object o) {
        log.info(o.toString());
    }
}
