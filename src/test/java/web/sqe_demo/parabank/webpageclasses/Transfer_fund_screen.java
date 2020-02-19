package web.sqe_demo.parabank.webpageclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.utilities.BaseClass;

public class Transfer_fund_screen extends BaseClass {
	public static String projectName = "sqe_demo";
	public WebDriver driver;

	public Transfer_fund_screen(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='amount']")	
	private WebElement	Input_364904input;
	public String fillinputInput_364904(String varInputValue) {
		waitForExpectedElement(driver, Input_364904input);
		Input_364904input.sendKeys(varInputValue);
		return text;
	}

	@FindBy(how = How.XPATH, using = "//div[@id='rightPanel']/div[1]/div[1]/form[1]/div[2]/input[1]")	
	private WebElement	Transfer_364913input;
	public String clkinputTransfer_364913() {
		waitForExpectedElement(driver, Transfer_364913input);		
		String text = Transfer_364913input.getText();
		Transfer_364913input.click();
		return text;
	}

}