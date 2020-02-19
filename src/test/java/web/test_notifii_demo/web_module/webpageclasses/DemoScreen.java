package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;

public class DemoScreen extends BaseClass {
	public static String projectName = "test_notifii_demo";
	public WebDriver driver;

	public DemoScreen(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='password']")	
	private WebElement	Password_446794input;
	public String fillinputPassword_446794(String varInputValue) {
		waitForExpectedElement(driver, Password_446794input);
		Password_446794input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Still need help? Call us at 888-506-8536 or email us at .')]")	
	private WebElement	StillNeedHelpCallUsAt8885068536OrEmailUsAt_446799div;
	public String textdivStillNeedHelpCallUsAt8885068536OrEmailUsAt_446799() {
		waitForExpectedElement(driver, StillNeedHelpCallUsAt8885068536OrEmailUsAt_446799div);		
		String text = StillNeedHelpCallUsAt8885068536OrEmailUsAt_446799div.getText();
		StillNeedHelpCallUsAt8885068536OrEmailUsAt_446799div.click();
		return text;
	}

	@FindBy(how = How.XPATH, using = "//form[@id='login-form']/fieldset[1]/section[2]/div[1]/label")	
	private WebElement	Password_446800label;
	public String textlabelPassword_446800() {
		waitForExpectedElement(driver, Password_446800label);		
		String text = Password_446800label.getText();
		Password_446800label.click();
		return text;
	}

	@FindBy(how = How.CSS, using = "html > body > div.wrapper.boxed-layout > div.container.content > div.row:nth-child(1) > div.col-xs-12 > p > a > img")	
	private WebElement	Notifii_446801img;
	public String clkimgNotifii_446801() {
		waitForExpectedElement(driver, Notifii_446801img);		
		String text = Notifii_446801img.getText();
		Notifii_446801img.click();
		return text;
	}

}