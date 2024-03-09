package pl.wmsdev;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import pl.wmsdev.usos4jheadless.HeadlessUsosProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class HeadlessUsosProviderExtension implements BeforeAllCallback {

    private static final int INTEGRATION_TEST_THROTTLE_VALUE = 500;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println(Resources.toString(this.getClass().getClassLoader().getResource("logo.txt"), Charsets.UTF_8));
        var baseUrl = System.getenv("usos_baseurl");
        var consumerKey = System.getenv("usos_consumerKey");
        var consumerSecret = System.getenv("usos_consumerSecret");
        var login = System.getenv("usos_login");
        var password = System.getenv("usos_password");
        HeadlessUsosHolder.setHeadlessUsos(new HeadlessUsosProvider().
                get(baseUrl, consumerKey, consumerSecret, login, password));
        throttleRequest();
    }

    @SneakyThrows
    private void throttleRequest() {
        Thread.sleep(INTEGRATION_TEST_THROTTLE_VALUE);
    }
}
