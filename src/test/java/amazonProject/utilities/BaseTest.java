package amazonProject.utilities;

import amazonProject.page.homePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;


public class BaseTest {

    homePage Hp = new homePage();


    @BeforeMethod()
    public void login(){

        Driver.getDriver().get("https://www.amazon.com/");
        Hp.helloSign.click();
        Hp.emailPhoneArea.click();
        Hp.emailPhoneArea.sendKeys("nihanaydin313@gmail.com");
        Hp.countinueButtonEmail.click();
        Hp.signInPasswordArea.click();
        Hp.signInPasswordArea.sendKeys("313Amazon313.");
        Hp.signInSubmit.click();
        Assert.assertTrue(Hp.amazonLogo.isDisplayed());
    }

    @AfterMethod()
    public void tearDown(){Driver.close();}















}
