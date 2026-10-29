package pl.wmsdev.usos4jheadless;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PwOAuthVerifierExtractionStrategy implements HeadlessOauthVerifierExtractionStrategy {
    @Override
    public String extractVerifier(WebDriver driver, String login, String password) {
        var usernameInput = driver.findElement(By.id("username"));
        var passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys(login);
        passwordInput.sendKeys(password);

        driver.findElement(By.name("submit")).click();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("local-allow")),
                ExpectedConditions.presenceOfElementLocated(By.className("local-pin"))));
        driver.findElements(By.id("local-allow")).forEach(WebElement::click);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.className("local-pin"))).getText();
    }
}
