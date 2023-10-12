package amazonProject.test;

import amazonProject.page.homePage;
import amazonProject.utilities.BaseTest;
import org.testng.annotations.Test;

public class US_02 extends BaseTest {
    homePage homePage = new homePage();

    @Test
    public void autologin(){
        homePage.us02();
    }
}
