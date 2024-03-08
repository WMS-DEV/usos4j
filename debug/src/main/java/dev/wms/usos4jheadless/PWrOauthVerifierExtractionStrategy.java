package dev.wms.usos4jheadless;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PWrOauthVerifierExtractionStrategy implements HeadlessOauthVerifierExtractionStrategy {
    @Override
    public String extractVerifier(WebDriver driver, String login, String password) {
        var username = driver.findElement(By.id("username"));
        var passwd = driver.findElement(By.id("password"));
        username.sendKeys(login);
        passwd.sendKeys(password);
        driver.findElement(By.className("login")).click();
        return driver.findElement(By.className("local-pin")).getText();
    }
}
