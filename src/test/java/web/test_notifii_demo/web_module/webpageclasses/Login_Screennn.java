package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;

public class Login_Screennn extends BaseClass {
	public static String projectName = "test_notifii_demo";
	public WebDriver driver;

	public Login_Screennn(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='username']")	
	private WebElement	Username_445806input;
	public String fillinputUsername_445806(String varInputValue) {
		waitForExpectedElement(driver, Username_445806input);
		Username_445806input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='password']")	
	private WebElement	Password_445810input;
	public String fillinputPassword_445810(String varInputValue) {
		waitForExpectedElement(driver, Password_445810input);
		Password_445810input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//form[@id='login-form']/fieldset[1]/section[4]/div[1]/div[2]/button")	
	private WebElement	LogIn_445819button;
	public String clkbuttonLogIn_445819() {
		waitForExpectedElement(driver, LogIn_445819button);		
		String text = LogIn_445819button.getText();
		LogIn_445819button.click();
		return text;
	}

}