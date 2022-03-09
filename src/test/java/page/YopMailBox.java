package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YopMailBox extends AbstractPage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath = "//button[@onclick='egengo();']")
    private WebElement checkMailBox;

    @FindBy(xpath = "//button[@id='refresh']")
    private WebElement refreshMailBox;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement emailFrame;

    @FindBy(xpath = "//h3[contains(text(),'USD')]")
    private WebElement totalCost;

    public YopMailBox(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTotalCost() {
        checkMailBox.click();
        refreshMailBox.click();
        driver.switchTo().frame(emailFrame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'USD')]")));

        return totalCost.getText();
    }


}
