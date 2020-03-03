package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

@SuppressWarnings("unused")
public class Package_history_screen extends BaseClass {
	
	public static String primaryInfo  = "{\"user_id\":7,\"executed_user_id\":7,\"is_generate\":false,\"is_execute\":false,\"is_web\":true,\"project_url\":\"https://www.devrabbitdev.com/login.php\",\"report_upload_url\":\"https://smartqe.io:443/UploadReportFile\",\"project_name\":\"test_notifii_demo\",\"project_description\":\"desc\",\"project_id\":447,\"module_name\":\"web_module\",\"module_description\":\"desc\",\"sub_module_id\":0,\"module_id\":761,\"testcase_name\":\"TC_Notifii_Package\",\"testcase_id\":744,\"testset_id\":0,\"executed_timestamp\":-1607080743,\"browser_type\":\"chrome\",\"testcase_overwrite\":true,\"client_timezone_id\":\"Asia/Calcutta\"}";

	public static String projectName = "test_notifii_demo";
	public WebDriver driver;
	public ExtentReports reports;
	public ExtentTest test;
	public static final int datasetsLength = 1;

	public Package_history_screen(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//div[@id='tab-track-primary']/div/ul/li[1]/a")	
	private WebElement	Packages_422454a;
	public String clkaPackages_422454() {
		waitForExpectedElement(driver, Packages_422454a);		
		String text = Packages_422454a.getText();
		Packages_422454a.click();
		return text;
	}

	@FindBy(how = How.XPATH, using = "//div[@id='tab-track-primary']/div/ul/li[1]/ul[1]/li[3]/a")	
	private WebElement	PackageHistory_422455a;
	public String clkaPackageHistory_422455() {
		waitForExpectedElement(driver, PackageHistory_422455a);		
		String text = PackageHistory_422455a.getText();
		PackageHistory_422455a.click();
		return text;
	}

	@FindBy(how = How.XPATH, using = "//form[@id='package-history-form']/fieldset[1]/section[5]/div[1]/div[2]/button")	
	private WebElement	UpdatePackageHistory_422413button;
	public String clkbuttonUpdatePackageHistory_422413() {
		waitForExpectedElement(driver, UpdatePackageHistory_422413button);		
		String text = UpdatePackageHistory_422413button.getText();
		UpdatePackageHistory_422413button.click();
		return text;
	}

}