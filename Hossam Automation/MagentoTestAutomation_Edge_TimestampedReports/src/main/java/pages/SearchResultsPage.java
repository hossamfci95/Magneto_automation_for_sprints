package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


    public String CheckSearchCorrect() throws InterruptedException {

        By SearchTerm = By.className("message notice");
        WebElement SearchTermElement = driver.findElement(SearchTerm);


        return SearchTermElement.getText();


    }
    public boolean CheckInvalid() throws InterruptedException {

        By Invalid = By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div");
        WebElement InvalidMessage = driver.findElement(Invalid);


        return InvalidMessage.isDisplayed();


    }
    public void AddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement Item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-item-name")));

       // js.executeScript("arguments[0].scrollIntoView(true);", Item);

        Item.click();

// Wait and click on Size
        WebElement Size = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-size-143-item-166")));
        js.executeScript("arguments[0].scrollIntoView(true);", Size);
        Size.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner"))); // Wait for reload

// Wait and click on Color
        WebElement Color = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-color-93-item-56")));
        js.executeScript("arguments[0].scrollIntoView(true);", Color);
        Color.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner"))); // Wait for reload

// Wait and click on Add to Cart
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
        addToCart.click();

    }
    public boolean CheckCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

// Wait and click on Cart icon
        WebElement Cart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")));
        js.executeScript("arguments[0].scrollIntoView(true);", Cart);
        Cart.click();

// Wait for the minicart items to be visible
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-image-photo")));
        return item.isDisplayed();
    }}
