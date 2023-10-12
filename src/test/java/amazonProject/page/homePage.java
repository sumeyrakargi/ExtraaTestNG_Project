package amazonProject.page;



import amazonProject.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class homePage {

    public homePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath="//a[@id='nav-link-accountList']")
    public WebElement helloSign;

    @FindBy(id = "ap_email")
    public WebElement emailPhoneArea;

    @FindBy(css = "input[id='continue']")
    public WebElement countinueButtonEmail;

    @FindBy(id = "ap_password")
    public WebElement signInPasswordArea;

    @FindBy(id = "signInSubmit")
    public WebElement signInSubmit;

    @FindBy(id = "nav-logo-sprites")
    public WebElement amazonLogo;








    public void us01(){

        Driver.getDriver().get("https://www.amazon.com/");
        Driver.close();

    }

    public void us02(){



    }





















}
