package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ExcelUtils;
import utils.JsonUtils;

import java.lang.reflect.Method;

public class HoodiesTest extends BaseTest {

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver); // Now driver is initialized
        resultsPage = new SearchResultsPage(driver);
    }

    protected void testSearchForProduct() {
        homePage.searchForItem("Hoodie");
    }
    @Test(dataProvider = "excelData")
    //1

    public void testSearchIsCorrect(String hoodieName) throws InterruptedException {
        Object test = extent.createTest("Excel Data Test - Hoodie: " + hoodieName);

        testSearchForProduct();
        String result = resultsPage.CheckSearchCorrect();
        Assert.assertTrue(result.contains("Hoodie"), "Search result validation failed!");
    }
//2
    @Test()
    public void InvalidSearch(JSONObject data) throws InterruptedException {
        homePage.EnterText("#%6");
        Assert.assertTrue(resultsPage.CheckInvalid(), "Invalid search not displayed correctly!");
    }
  //3
    @Test()
    public void AddingToCart(String hoodieName) throws InterruptedException {
        homePage.searchForItem("Hoodie");

        resultsPage.AddToCart();
    }
        @DataProvider(name = "excelData")
    public Object[][] getExcelData(Method method) {
            ExcelUtils excel = new ExcelUtils("resources/data.xlsx", "Sheet1");
            return new Object[][]{
                    {excel.getCellData(1, 0)},
                    {excel.getCellData(2, 0)}
            };

        }}
