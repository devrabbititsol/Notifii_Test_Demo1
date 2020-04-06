package web.test_notifii_demo.web_module.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;

public class Package_history_screen extends BaseClass {
	public static String projectName = "test_notifii_demo";
	public WebDriver driver;

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