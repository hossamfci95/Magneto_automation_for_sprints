package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.SearchResultsPage;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected HomePage homePage;
    protected SearchResultsPage resultsPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "edgedriver_win64/msedgedriver.exe");
        new File("reports").mkdirs(); // Ensure reports directory exists
        String timestamp = LocalDateTime.now().toString().replace(":", "-").replace(".", "-");
        String reportPath = "reports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://magento.softwaretestingboard.com/");
    }
    public void setUpPages() {
        homePage = new HomePage(driver); // Now driver is initialized
        resultsPage = new SearchResultsPage(driver);
    }

    protected void testSearchForProduct() {
        homePage.searchForItem("Hoodie");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
