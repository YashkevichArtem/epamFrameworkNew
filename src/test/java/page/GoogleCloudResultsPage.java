package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudResultsPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='gs-title']//*[contains(text(),'Google Cloud Pricing Calculator')]")
    private WebElement googleCalculatorLink;

    @FindBy (xpath = "//div[@class='devsite-searchbox']//input[@name='q']")
    private WebElement searchBox;

    public GoogleCloudResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void getCalculator() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//div[@class='gs-title']//*[contains(text(),'Google Cloud Pricing Calculator')]")));
        logger.info("Calculater opened");
        googleCalculatorLink.click();
    }

}
