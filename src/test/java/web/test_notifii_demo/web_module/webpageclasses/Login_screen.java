package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;

public class Login_screen extends BaseClass {
	public static String projectName = "test_notifii_demo";
	public WebDriver driver;

	public Login_screen(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='username']")	
	private WebElement	Username_422212input;
	public String fillinputUsername_422212(String varInputValue) {
		waitForExpectedElement(driver, Username_422212input);
		Username_422212input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='password']")	
	private WebElement	Password_422216input;
	public String fillinputPassword_422216(String varInputValue) {
		waitForExpectedElement(driver, Password_422216input);
		Password_422216input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//form[@id='login-form']/fieldset[1]/section[4]/div[1]/div[2]/button")	
	private WebElement	LogIn_422225button;
	public String clkbuttonLogIn_422225() {
		waitForExpectedElement(driver, LogIn_422225button);		
		String text = LogIn_422225button.getText();
		LogIn_422225button.click();
		return text;
	}

}