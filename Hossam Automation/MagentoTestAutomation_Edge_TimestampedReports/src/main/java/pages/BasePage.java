package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected EdgeDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = (EdgeDriver) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    public void enterText(By locator, String text) {
        waitForElement(locator);
        driver.findElement(locator).sendKeys(text);
        driver.findElement(locator).sendKeys(Keys.ENTER);

    }

    public String getText(By locator) {
        waitForElement(locator);
        return driver.findElement(locator).getText();
    }
}
