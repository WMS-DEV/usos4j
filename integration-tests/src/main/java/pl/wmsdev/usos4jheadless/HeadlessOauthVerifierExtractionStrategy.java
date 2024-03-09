package pl.wmsdev.usos4jheadless;

import org.openqa.selenium.WebDriver;

public interface HeadlessOauthVerifierExtractionStrategy {
    String extractVerifier(WebDriver driver, String login, String password);
}
