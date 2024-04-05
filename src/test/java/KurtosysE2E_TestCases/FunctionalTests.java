package KurtosysE2E_TestCases;

import KurtosysPageObjects.HomePage;
import KurtosysPageObjects.ResourcePage;
import KurtosysPageObjects.UCITSWhitePaperPage;
import KurtosysPageObjects.WhitePapersPage;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.*;


import java.io.IOException;

public class FunctionalTests extends BaseClass{

    @BeforeTest
    public void startReport(){
        initializeReport();
    }

    @Test
    public void verifySiteResourcesTest_001() throws InterruptedException, IOException {
        extentTest = extent.createTest("Verify Kurtosys Resources", "User should navigate to the site resources successfully");
        HomePage homePage = new HomePage((driver));
        homePage.click_KurtosysInsightTab();
        extentTest.info("Navigate to Kurtosys resource page.");
        ResourcePage resourcePage = new ResourcePage(driver);
        resourcePage.verifyResourcePageTitle(expectedResourceTitle);
    }

    @Test
    public void verifyWhitePaperDownloadTest_002() throws InterruptedException, IOException {
        extentTest = extent.createTest("Verify Kurtosys White Papers Functionality", "User should navigate to White Papers page successfully");
        extentTest.info("Navigate to the White Papers & eBooks page.");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToWhitePapersAnd_eBooks();
        WhitePapersPage whitePapersPage = new WhitePapersPage(driver);
        whitePapersPage.verifyWhitePapersTitle();
        extentTest.info("User open different White Papers on the Kurtosys site.");
        whitePapersPage.click_UCITSWhitePapers();
        extentTest.info("User fills the White Paper Download form.");
        UCITSWhitePaperPage ucitsWhitePaperPage = new UCITSWhitePaperPage(driver);
        ucitsWhitePaperPage.userFillWhitePaperDownloadForm(firstName,lastName,email,company,industry);
        extentTest.info("Submit the form.");
        ucitsWhitePaperPage.click_SendMeCopyButton();
        ucitsWhitePaperPage.validateErrorMessage();
}
    @AfterTest
    public void endReport(){
        extent.flush();
    }
}
