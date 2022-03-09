package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudHomePage extends AbstractPage {

    @FindBy (xpath = "//div[@class='devsite-searchbox']//input[@name='q']")
    private WebElement searchBox;

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private final Logger logger = LogManager.getRootLogger();

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage(){
        driver.navigate().to(HOMEPAGE_URL);
        logger.info("Open home page " + HOMEPAGE_URL);
        return this;
    }

    public void searchCalculator() {

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='devsite-searchbox']//input[@name='q']")));
        searchBox.click();
        searchBox.sendKeys("Google Cloud Platform Pricing Calculator");
        searchBox.submit();
    }

}
