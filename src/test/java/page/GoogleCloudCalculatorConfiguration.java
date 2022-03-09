package page;

import model.Order;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleCloudCalculatorConfiguration extends AbstractPage {

    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement myFrame;

    @FindBy(xpath = "//*[@id='input-0']")
    private WebElement searchPanel;

    @FindBy(xpath = "//*[@id='mainForm']/div[1]/md-icon")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    private WebElement fieldSeries;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement setSeriesElement;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement selectMachineType;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement setMachineTypeElement;

    @FindBy(xpath = "//button[@class='devsite-snackbar-action']")
    private WebElement buttonGoogleCookie;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement setAddGPUs;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement selectGpuTypes;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    private WebElement setGpuType;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement selectNumbersOfGPUs;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement setNumberOfGPUs;

    @FindBy(xpath = "//div[@class='ng-scope layout-row']//md-select[@placeholder='Local SSD']//div")
    private WebElement selectLocalSSD;

    @FindBy(xpath = "//md-option[@ng-value='item.value'][@value='2']")
    private WebElement setLocalSSD;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement selectDatacenterLocation;

    @FindBy(xpath = "//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3']")
    private WebElement setDatacenterLocation;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement selectCommittedUsage;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@ng-value='1']")
    private WebElement setCommittedUsage;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement buttonAddToEstimate;

    @FindBy(xpath = "//button[@aria-label='Email Estimate']")
    private WebElement buttonEmailEstimate;

    @FindBy(xpath = "//h2[@class='md-title']//b[@class='ng-binding']")
    private WebElement TotalEstimatedCost;

    public GoogleCloudCalculatorConfiguration(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setConfigurations(Order order) {
        List<WebElement> cookieButtons = driver.findElements(By.xpath("//button[@class='devsite-snackbar-action']"));
        if(cookieButtons.size() > 0){
            cookieButtons.get(0).click();
        }
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='myFrame']")));
        driver.switchTo().frame(myFrame);
        searchPanel.sendKeys(order.getProductName());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mainForm']/div[1]/md-icon")));
        searchButton.click();

        numberOfInstances.click();
        numberOfInstances.sendKeys(order.getNumberOfInstances());

        fieldSeries.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-option[@value='n1']")));
        setSeriesElement.click();

        selectMachineType.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")));
        setMachineTypeElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")));
        setAddGPUs.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-select[@placeholder='GPU type']")));
        selectGpuTypes.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-option[@value='NVIDIA_TESLA_V100']")));
        setGpuType.click();

        selectNumbersOfGPUs.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")));
        setNumberOfGPUs.click();

        selectDatacenterLocation.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3']")));
        setDatacenterLocation.click();

        selectLocalSSD.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-option[@ng-value='item.value'][@value='2']//div[@class='md-text ng-binding']")));
        setLocalSSD.click();

        selectCommittedUsage.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']//md-option[@ng-value='1']")));
        setCommittedUsage.click();

        buttonAddToEstimate.click();
        buttonEmailEstimate.click();
    }

    public String getTotalEstimatedCost() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='md-title']//b[@class='ng-binding']")));
        String input = TotalEstimatedCost.getText().replaceAll("Total Estimated Cost: ", "");
        return input.replaceAll(" per 1 month", "");
        //visibilityOfElementLocated(By.xpath("//h2[@class='md-title']//b[@class='ng-binding']")));
    }


}
