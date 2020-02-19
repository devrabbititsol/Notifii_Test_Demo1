package web.sqe_demo.parabank.webtestclasses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import web.sqe_demo.parabank.webpageclasses.Transfer_fund_screen;
import web.sqe_demo.parabank.webpageclasses.Screen_Test;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;
import com.utilities.Utilities;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class TC_Parabank extends BaseClass {
	private Logger logger;
	private ConfigFilesUtility configFileObj;
	private String browserName = "chrome";
	public boolean isElementDispalyed = false;


	public TC_Parabank() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TC_Parabank.class);
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("tc_parabank.properties");
	}

	@BeforeTest
	@Parameters("browser")
	public void browserName(String browser) throws Exception {
		browserName = browser;
	}

	
	public void setUP() throws Exception {
		driver = launchBrowser(browserName, configFileObj);
	}

	public void Transfer_fund_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 1;
		Transfer_fund_screen objTransfer_fund_screen = PageFactory.initElements(driver, Transfer_fund_screen.class);
		testLogHeader("Verify Transfer_fund_screen page");
		objTransfer_fund_screen.fillinputInput_364904(configFileObj.getProperty("Input"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Input : " + configFileObj.getProperty("Input"+ i + datasetScreencount));
		objTransfer_fund_screen.clkinputTransfer_364913();
		printInfoLogAndReport(logger, "Clicked on TransferButton");
		
	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport( logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	

	public void Screen_TestTest(int i) throws Exception {

	 try{
		int datasetScreencount = 2;
		Screen_Test objScreen_Test = PageFactory.initElements(driver, Screen_Test.class);
		testLogHeader("Verify Screen_Test page");
		objScreen_Test.clkAAboutUs_371534();
		printSuccessLogAndReport( logger,  "Text is displayed as : AboutUsLink");
		objScreen_Test.clkAAdminPage_371531();
		printSuccessLogAndReport( logger,  "Text is displayed as : AdminPageLink");

	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport( logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void screensTest() throws Exception {
		for(int datasets = 1; datasets <= configFileObj.getIntProperty("datasetsLength"); datasets++) {
			isElementDispalyed = true;			
			setUP();			
			setTestcaseName(browserName,"TC_Parabank - " + configFileObj.getProperty("dataset" + (datasets)));
			if(isElementDispalyed) { Transfer_fund_screenTest(datasets);}
			if(isElementDispalyed) { Screen_TestTest(datasets);}
			tearDown();
		}	}

	
	public void tearDown() throws Exception {
		driver.quit();
	}
}