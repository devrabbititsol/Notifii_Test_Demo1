package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

@SuppressWarnings("unused")
public class Home_screen extends BaseClass {
	
	public static String primaryInfo  = "{\"user_id\":7,\"executed_user_id\":7,\"is_generate\":false,\"is_execute\":false,\"is_web\":true,\"project_url\":\"https://www.devrabbitdev.com/login.php\",\"report_upload_url\":\"https://smartqe.io:443/UploadReportFile\",\"project_name\":\"test_notifii_demo\",\"project_description\":\"desc\",\"project_id\":447,\"module_name\":\"web_module\",\"module_description\":\"desc\",\"sub_module_id\":0,\"module_id\":761,\"testcase_name\":\"TC_Notifii_Package\",\"testcase_id\":744,\"testset_id\":0,\"executed_timestamp\":2061149295,\"browser_type\":\"chrome\",\"testcase_overwrite\":true,\"client_timezone_id\":\"Asia/Calcutta\"}";

	public static String projectName = "test_notifii_demo";
	public WebDriver driver;
	public ExtentReports reports;
	public ExtentTest test;
	public static final int datasetsLength = 1;

	public Home_screen(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//textarea[@id='tracking_number']")	
	private WebElement	Tracking_number_422230textarea;
	public String filltextareaTracking_number_422230(String varInputValue) {
		waitForExpectedElement(driver, Tracking_number_422230textarea);
		Tracking_number_422230textarea.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//span[@id='select2-chosen-1']")	
	private WebElement	Search_422279span;
	public String clkspanSearch_422279() {
		waitForExpectedElement(driver, Search_422279span);		
		String text = Search_422279span.getText();
		Search_422279span.click();
		return text;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='s2id_autogen1_search']")	
	private WebElement	Select2results1_422235input;
	public String fillinputSelect2results1_422235(String varInputValue) {
		waitForExpectedElement(driver, Select2results1_422235input);
		Select2results1_422235input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = " //*[@id='select2-result-label-2']")	
	private WebElement	Select_name_422543DIV;
	public String clkDIVSelect_name_422543() {
		waitForExpectedElement(driver, Select_name_422543DIV);		
		String text = Select_name_422543DIV.getText();
		Select_name_422543DIV.click();
		return text;
	}

	@FindBy(how = How.XPATH, using = "//form[@id='package-login-form']/fieldset[2]/section[3]/div/div[1]/label/textarea")	
	private WebElement	Custom_message_422238textarea;
	public String filltextareaCustom_message_422238(String varInputValue) {
		waitForExpectedElement(driver, Custom_message_422238textarea);
		Custom_message_422238textarea.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//button[@id='submit_button']")	
	private WebElement	LogThisPackageIn_422314button;
	public String clkbuttonLogThisPackageIn_422314() {
		waitForExpectedElement(driver, LogThisPackageIn_422314button);		
		String text = LogThisPackageIn_422314button.getText();
		LogThisPackageIn_422314button.click();
		return text;
	}

}