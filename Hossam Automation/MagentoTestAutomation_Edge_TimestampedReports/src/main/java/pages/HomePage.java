package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By searchBox = By.id("search");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void EnterText(String item) {
        enterText(searchBox, item);
    }
    public void searchForItem(String item) {
        enterText(searchBox, item);

   }
}
