package pl.wmsdev.usos4jheadless;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

@Slf4j
public class HeadlessOAuthAuthenticator {

    private final Map<String, HeadlessOauthVerifierExtractionStrategy> extractionStrategies = Map.of(
            "https://apps.usos.pwr.edu.pl/", new PWrOauthVerifierExtractionStrategy(),
            "https://apps.usos.pw.edu.pl/", new PwOAuthVerifierExtractionStrategy()
    );

    public String authenticate(String authUrl, String login, String password) {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);
        driver.get(authUrl);
        var result = getStrategyFor(authUrl).extractVerifier(driver, login, password);
        driver.quit();
        log.info("Properly obtained verifier from {}", authUrl);
        return result;
    }

    private HeadlessOauthVerifierExtractionStrategy getStrategyFor(String authUrl) {
        return extractionStrategies.entrySet().stream()
                .filter(entry -> authUrl.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Couldn't find strategy to handle %s".formatted(authUrl)));
    }


}
