package pl.wmsdev.usos4jheadless;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class HeadlessOAuthAuthenticator {

    private final Map<String, HeadlessOauthVerifierExtractionStrategy> extractionStrategies;
    private final Supplier<WebDriver> webDriverSupplier;

    public HeadlessOAuthAuthenticator() {
        this(
                Map.of(
                        "https://apps.usos.pwr.edu.pl/", new PWrOauthVerifierExtractionStrategy(),
                        "https://apps.usos.pw.edu.pl/", new PwOAuthVerifierExtractionStrategy()
                ),
                () -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    return new ChromeDriver(options);
                }
        );
    }

    HeadlessOAuthAuthenticator(
            Map<String, HeadlessOauthVerifierExtractionStrategy> extractionStrategies,
            Supplier<WebDriver> webDriverSupplier
    ) {
        this.extractionStrategies = extractionStrategies;
        this.webDriverSupplier = webDriverSupplier;
    }

    public String authenticate(String authUrl, String login, String password) {
        WebDriver driver = webDriverSupplier.get();
        try {
            driver.get(authUrl);
            var result = getStrategyFor(authUrl).extractVerifier(driver, login, password);
            log.info("Properly obtained verifier from {}", authUrl);
            return result;
        } finally {
            driver.quit();
        }
    }

    private HeadlessOauthVerifierExtractionStrategy getStrategyFor(String authUrl) {
        return extractionStrategies.entrySet().stream()
                .filter(entry -> authUrl.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseGet(FakeUsosOauthVerifierExtractionStrategy::new);
    }

}
