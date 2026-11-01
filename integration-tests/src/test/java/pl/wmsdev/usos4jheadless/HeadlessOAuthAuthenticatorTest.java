package pl.wmsdev.usos4jheadless;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeadlessOAuthAuthenticatorTest {

    @Test
    void authenticateExtractsVerifierForEachCall() {
        AtomicInteger extractionCount = new AtomicInteger();
        HeadlessOauthVerifierExtractionStrategy strategy = (driver, login, password) -> {
            extractionCount.incrementAndGet();
            return "verifier";
        };
        AtomicInteger createdDrivers = new AtomicInteger();
        HeadlessOAuthAuthenticator authenticator = new HeadlessOAuthAuthenticator(
                Map.of("https://apps.usos.pwr.edu.pl/", strategy),
                countingWebDriverSupplier(createdDrivers)
        );

        String first = authenticator.authenticate(
                "https://apps.usos.pwr.edu.pl/services/oauth/authorize?oauth_token=one",
                "cache-user",
                "password"
        );
        String second = authenticator.authenticate(
                "https://apps.usos.pwr.edu.pl/services/oauth/authorize?oauth_token=two",
                "cache-user",
                "password"
        );

        assertEquals("verifier", first);
        assertEquals("verifier", second);
        assertEquals(2, extractionCount.get());
        assertEquals(2, createdDrivers.get());
    }

    @Test
    void authenticateDoesNotCacheFailedExtraction() {
        AtomicInteger extractionCount = new AtomicInteger();
        HeadlessOauthVerifierExtractionStrategy strategy = (driver, login, password) -> {
            if (extractionCount.incrementAndGet() == 1) {
                throw new IllegalStateException("First extraction fails");
            }
            return "verifier-after-retry";
        };
        AtomicInteger createdDrivers = new AtomicInteger();
        HeadlessOAuthAuthenticator authenticator = new HeadlessOAuthAuthenticator(
                Map.of("https://apps.usos.pwr.edu.pl/", strategy),
                countingWebDriverSupplier(createdDrivers)
        );

        assertThrows(
                IllegalStateException.class,
                () -> authenticator.authenticate(
                        "https://apps.usos.pwr.edu.pl/services/oauth/authorize?oauth_token=one",
                        "failing-user",
                        "password"
                )
        );
        String second = authenticator.authenticate(
                "https://apps.usos.pwr.edu.pl/services/oauth/authorize?oauth_token=two",
                "failing-user",
                "password"
        );

        assertEquals("verifier-after-retry", second);
        assertEquals(2, extractionCount.get());
        assertEquals(2, createdDrivers.get());
    }

    private static Supplier<WebDriver> countingWebDriverSupplier(AtomicInteger createdDrivers) {
        return () -> {
            createdDrivers.incrementAndGet();
            return noOpWebDriver();
        };
    }

    private static WebDriver noOpWebDriver() {
        return (WebDriver) Proxy.newProxyInstance(
                WebDriver.class.getClassLoader(),
                new Class[]{WebDriver.class},
                (proxy, method, args) -> defaultValue(method.getReturnType())
        );
    }

    private static Object defaultValue(Class<?> type) {
        if (!type.isPrimitive()) {
            return null;
        }
        if (boolean.class.equals(type)) {
            return false;
        }
        if (byte.class.equals(type)) {
            return (byte) 0;
        }
        if (short.class.equals(type)) {
            return (short) 0;
        }
        if (int.class.equals(type)) {
            return 0;
        }
        if (long.class.equals(type)) {
            return 0L;
        }
        if (float.class.equals(type)) {
            return 0F;
        }
        if (double.class.equals(type)) {
            return 0D;
        }
        if (char.class.equals(type)) {
            return '\0';
        }
        return null;
    }
}
