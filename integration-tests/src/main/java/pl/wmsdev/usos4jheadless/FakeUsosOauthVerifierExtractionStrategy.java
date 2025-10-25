package pl.wmsdev.usos4jheadless;

import org.openqa.selenium.WebDriver;

public class FakeUsosOauthVerifierExtractionStrategy implements HeadlessOauthVerifierExtractionStrategy {

    @Override
    public String extractVerifier(WebDriver driver, String login, String password) {
        // fake-usos doesn't support oauth authentication for now
        return "";
    }

}
