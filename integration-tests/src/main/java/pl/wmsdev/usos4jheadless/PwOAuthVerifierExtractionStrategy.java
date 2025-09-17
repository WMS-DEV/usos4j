package pl.wmsdev.usos4jheadless;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PwOAuthVerifierExtractionStrategy implements HeadlessOauthVerifierExtractionStrategy {
    @Override
    public String extractVerifier(WebDriver driver, String login, String password) {
        var usernameInput = driver.findElement(By.id("username"));
        var passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys(login);
        passwordInput.sendKeys(password);

        driver.findElement(By.name("submit")).click();
        return driver.findElement(By.className("local-pin")).getText();
    }
}
