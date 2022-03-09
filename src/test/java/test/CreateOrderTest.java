package test;

import model.Order;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;
import service.OrderCreator;

public class CreateOrderTest extends CommonConditions {

    @Test(priority = 0)
    public void homePageTest() {
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver);
        homePage.openPage().searchCalculator();

    }

    @Test(priority = 1)
    public void setCalculatorTest() {
        GoogleCloudResultsPage resultsPage = new GoogleCloudResultsPage(driver);
        resultsPage.getCalculator();
    }

    @Test(priority = 2)
    public void setCalculatorConfiguration() {
        Order testOrder = OrderCreator.withCredentialsFromProperty();
        GoogleCloudCalculatorConfiguration calcConf = new GoogleCloudCalculatorConfiguration(driver);
        calcConf.setConfigurations(testOrder);
    }

    @Test(priority = 3)
    public void createMail() throws InterruptedException {
        String totalEstimatedCost = new GoogleCloudCalculatorConfiguration(driver).getTotalEstimatedCost();
        YopMailGenerator mailGenerator = new YopMailGenerator(driver);
        YopMailBox mailBox = new YopMailBox(driver);
        mailGenerator.mailService();
        mailGenerator.emailGenerator();
        Assert.assertTrue(mailBox.getTotalCost().contains(totalEstimatedCost));

    }
}
