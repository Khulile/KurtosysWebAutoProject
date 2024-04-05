package KurtosysPageObjects;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;
import static KurtosysE2E_TestCases.BaseClass.*;
public class ResourcePage {
    private  WebDriver driver;
    public ResourcePage(WebDriver driver){
        this.driver=driver;
    }
    public  void verifyResourcePageTitle(String expectedTitle) throws IOException {
        String actualResourceTitle = driver.getTitle();
        Assert.assertEquals(actualResourceTitle, expectedTitle);
        if (actualResourceTitle.equals( expectedTitle)){
            extentTest.log(Status.PASS, "Test passed.");
        }
        else{
            extentTest.log(Status.FAIL, "Test Failed");
            extentTest.addScreenCaptureFromPath(captureScreenShot(driver));
        }
    }
}
