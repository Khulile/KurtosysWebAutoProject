package KurtosysPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Insights')]")
    private WebElement kurtosysInsightTab;
    @FindBy(how = How.XPATH, using = "//span[text()='White Papers & eBooks']")
    private WebElement whitePapersAnde_BooksLink;
    public void click_KurtosysInsightTab() throws InterruptedException {
        kurtosysInsightTab.click();
        Thread.sleep(2000);
    }
    public void navigateToWhitePapersAnd_eBooks() throws InterruptedException {
        Actions act = new Actions(driver);
        act.moveToElement(kurtosysInsightTab).perform();
        Thread.sleep(2000);
        whitePapersAnde_BooksLink.click();
        Thread.sleep(2000);
    }


}
