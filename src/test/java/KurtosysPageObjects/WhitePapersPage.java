package KurtosysPageObjects;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static KurtosysE2E_TestCases.BaseClass.*;

public class WhitePapersPage {
    private WebDriver driver;
    public WhitePapersPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public JavascriptExecutor js;

    public  void verifyWhitePapersTitle(){
        String expectedWPTitle = "White Papers| Kurtosys";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedWPTitle, actualTitle);
        if(actualTitle.equals(expectedWPTitle)){
            extentTest.log(Status.PASS, "Test Passed");
        }
        else {
            extentTest.log(Status.FAIL, "Test Failed");
            extentTest.addScreenCaptureFromPath(captureScreenShot(driver));
        }
    }
    public  void click_UCITSWhitePapers() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,191)");
        driver.findElement(By.xpath("//a[contains(text(),'UCITS White Paper')]")).click();
        Thread.sleep(2000);
    }
}
