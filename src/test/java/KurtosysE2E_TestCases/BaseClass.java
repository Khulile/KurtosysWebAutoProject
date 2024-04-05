package KurtosysE2E_TestCases;

import Utils.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    public static WebDriver driver;
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;
    ReadConfig readConfig = new ReadConfig();
    public static String baseUrl = "https://www.kurtosys.com/";
    public String expectedResourceTitle = readConfig.getExpectedResourceTitle();
    public String expectedWhitePapersTitle ="White Papers| Kurtosys";
    public String firstName = readConfig.getFirstName();
    public String lastName = readConfig.getLastName();
    public String email = readConfig.getEmail();
    public String company = readConfig.getCompany();
    public String industry = readConfig.getIndustry();

    @BeforeClass(alwaysRun = true)
    public static void launchBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);

    }
    public static void initializeReport(){
        extent = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/testReports.html");
        extent.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setOfflineMode(true);
        extentSparkReporter.config().setDocumentTitle("Kurtosys Automation Report");
        extentSparkReporter.config().setReportName("Kurtosys Test Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM, dd, yyyy, hh:mm a '('zzz')'");

        extent.setSystemInfo("Machine", "Onke-911");
        extent.setSystemInfo("Environment", "UAT");
        extent.setSystemInfo("Build", "Integration");
    }
    public static String captureScreenShot(WebDriver driver){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
        return path;

    }
    @AfterMethod
    public static void setTestResults(ITestResult result) throws IOException {

        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.fail(result.getName());
            extentTest.fail(result.getThrowable());
            extentTest.addScreenCaptureFromPath(captureScreenShot(driver));
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            extentTest.pass(result.getName());
            //extentTest.addScreenCaptureFromPath(captureScreenShot(driver));

        }
        else if (result.getStatus()==ITestResult.SKIP){
            extentTest.skip("Test Case:" + result.getName() + "has been skipped");
        }

    }


    @AfterClass(alwaysRun = true)
    public static void tearDown()
    {

        driver.close();

    }
}
