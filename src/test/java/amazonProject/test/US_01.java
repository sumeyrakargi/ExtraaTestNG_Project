package amazonProject.test;

import amazonProject.page.homePage;

import amazonProject.utilities.BaseTestReport;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class US_01 extends BaseTestReport {




    homePage homePage = new homePage();


    @Test
    @Description("User Story 1-1")
    public void us1tc1(){
        extentTest = extentReports.createTest("login test");
        homePage.us01();}
}
