package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class YopMailGenerator extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://yopmail.com/ru/";
    private static final String EMAIL_GENERATOR_URL = "https://yopmail.com/ru/email-generator";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private String windowCalculator;
    private String mailServiceUrl;
    private String emailAddress;

    @FindBy(xpath = "//button[@id='accept']")
    private WebElement buttonAcceptRecCookies;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement mainFrame;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement emailFrame;

    @FindBy(xpath = "//h3[contains(text(),'USD')]")
    private WebElement totalCost;

    @FindBy(xpath = "//div[@class='flexcx']//a[@href='email-generator']")
    private WebElement btnEmailGenerator;

    @FindBy(xpath = "//div[@id='egen']")
    private WebElement generatedEmail;

    @FindBy(xpath = "//form[@name='emailForm']//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement btnSendEmail;

    public YopMailGenerator(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void mailService() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        windowCalculator = driver.getWindowHandle();
        js.executeScript("window.open()");
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!window.equals(windowCalculator)){
                mailServiceUrl = window;
                break;
            }
        }
        driver.switchTo().window(mailServiceUrl);
        driver.get(HOMEPAGE_URL);
        List<WebElement> cookieButtons = driver.findElements(By.xpath("//button[@id='accept']"));
        if(cookieButtons.size() > 0){
            cookieButtons.get(0).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flexcx']//a[@href='email-generator']")));
        btnEmailGenerator.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='egen']")));
    }

    public void emailGenerator() {
        emailAddress = generatedEmail.getText();
        driver.switchTo().window(windowCalculator);
        driver.switchTo().frame(0);
        driver.switchTo().frame(mainFrame);
        emailField.click();
        emailField.sendKeys(emailAddress);
        btnSendEmail.click();
        driver.switchTo().window(mailServiceUrl);
    }

}
