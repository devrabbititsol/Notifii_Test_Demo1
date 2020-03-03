package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

@SuppressWarnings("unused")
public class Login_screen extends BaseClass {
	
	public static String primaryInfo  = "{\"user_id\":7,\"executed_user_id\":7,\"is_generate\":false,\"is_execute\":false,\"is_web\":true,\"project_url\":\"https://www.devrabbitdev.com/login.php\",\"report_upload_url\":\"https://smartqe.io:443/UploadReportFile\",\"project_name\":\"test_notifii_demo\",\"project_description\":\"desc\",\"project_id\":447,\"module_name\":\"web_module\",\"module_description\":\"desc\",\"sub_module_id\":0,\"module_id\":761,\"testcase_name\":\"TC_Notifii_Package\",\"testcase_id\":744,\"testset_id\":0,\"executed_timestamp\":-1607080743,\"browser_type\":\"chrome\",\"testcase_overwrite\":true,\"client_timezone_id\":\"Asia/Calcutta\"}";

	public static String projectName = "test_notifii_demo";
	public WebDriver driver;
	public ExtentReports reports;
	public ExtentTest test;
	public static final int datasetsLength = 1;

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