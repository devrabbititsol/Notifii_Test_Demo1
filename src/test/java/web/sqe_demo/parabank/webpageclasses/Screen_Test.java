package web.sqe_demo.parabank.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;

public class Screen_Test extends BaseClass {
	public static String projectName = "sqe_demo";
	public WebDriver driver;

	public Screen_Test(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//DIV[@id='headerPanel']/UL[1]/LI[2]/A[1]")	
	private WebElement	AboutUs_371534A;
	public String clkAAboutUs_371534() {
		waitForExpectedElement(driver, AboutUs_371534A);		
		String text = AboutUs_371534A.getText();
		AboutUs_371534A.click();
		return text;
	}

	@FindBy(how = How.XPATH, using = "//DIV[@id='headerPanel']/UL[1]/LI[6]/A[1]")	
	private WebElement	AdminPage_371531A;
	public String clkAAdminPage_371531() {
		waitForExpectedElement(driver, AdminPage_371531A);		
		String text = AdminPage_371531A.getText();
		AdminPage_371531A.click();
		return text;
	}

}