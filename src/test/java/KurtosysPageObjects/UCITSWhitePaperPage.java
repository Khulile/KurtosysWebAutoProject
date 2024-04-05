package KurtosysPageObjects;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

import static KurtosysE2E_TestCases.BaseClass.*;


public class UCITSWhitePaperPage {

    private WebDriver driver;

    public UCITSWhitePaperPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public JavascriptExecutor js;

    // The id's on the form are not dynamic. We cannot make use of them with no failure.
    public void userFillWhitePaperDownloadForm(String firstNameText, String lastNameText, String emailText, String companyText, String industryText) throws InterruptedException {
        // accept all cookies.
        driver.findElement(By.xpath("//button[contains(text(),'Accept All Cookies')]")).click();
        driver.switchTo().frame(0);
        //First Name field
        driver.findElement(By.id("18882_231669pi_18882_231669")).click();
        driver.findElement(By.id("18882_231669pi_18882_231669")).sendKeys(firstNameText);
        // Last Name field
        driver.findElement(By.id("18882_231671pi_18882_231671")).click();
        driver.findElement(By.id("18882_231671pi_18882_231671")).sendKeys(lastNameText);
        // Email field
        driver.findElement(By.id("18882_231673pi_18882_231673")).click();
        driver.findElement(By.id("18882_231673pi_18882_231673")).sendKeys(emailText);
        // Company field
        driver.findElement(By.id("18882_231675pi_18882_231675")).click();
        driver.findElement(By.id("18882_231675pi_18882_231675")).sendKeys(companyText);
        // Industry field
        driver.findElement(By.id("18882_231677pi_18882_231677")).click();
        driver.findElement(By.id("18882_231677pi_18882_231677")).sendKeys(industryText);
    }

    public void click_SendMeCopyButton() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,180)");
        driver.findElement(By.cssSelector(".submit > input")).click();
        Thread.sleep(2000);
    }

    public void validateErrorMessage() throws InterruptedException, IOException {
        //driver.switchTo().frame(0);
        String expectErrorMessage = "This field is required.";
        WebElement errorElement = driver.findElement(By.id("error_for_18882_231673pi_18882_231673"));
        String actualErrorMessage = errorElement.getText();
        Assert.assertEquals(actualErrorMessage, expectErrorMessage);
        if (actualErrorMessage.equals(expectErrorMessage)) {
            extentTest.log(Status.PASS, "Test Passed");
        } else {
            extentTest.log(Status.FAIL, "Test Failed");
            extentTest.addScreenCaptureFromPath(captureScreenShot(driver));
        }
    }
}
