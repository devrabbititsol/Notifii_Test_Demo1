package web.test_notifii_demo.web_module.webtestclasses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import web.test_notifii_demo.web_module.webpageclasses.Login_Screennn;
import web.test_notifii_demo.web_module.webpageclasses.DemoScreen;
import web.test_notifii_demo.web_module.webpageclasses.Package_history_screen;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;
import com.utilities.Utilities;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class TC_Demo_Testcase extends BaseClass {
	private Logger logger;
	private ConfigFilesUtility configFileObj;
	private String browserName = "chrome";
	public boolean isElementDispalyed = false;


	public TC_Demo_Testcase() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TC_Demo_Testcase.class);
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("tc_demo_testcase.properties");
	}

	@BeforeTest
	@Parameters("browser")
	public void browserName(String browser) throws Exception {
		browserName = browser;
	}

	
	public void setUP() throws Exception {
		driver = launchBrowser(browserName, configFileObj);
	}

	public void Login_ScreennnTest(int i) throws Exception {

	 try{
		int datasetScreencount = 1;
		Login_Screennn objLogin_Screennn = PageFactory.initElements(driver, Login_Screennn.class);
		testLogHeader("Verify Login_Screennn page");
		objLogin_Screennn.fillinputUsername_445806(configFileObj.getProperty("Username"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Username : " + configFileObj.getProperty("Username"+ i + datasetScreencount));
		objLogin_Screennn.fillinputPassword_445810(configFileObj.getProperty("Password"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Password : " + configFileObj.getProperty("Password"+ i + datasetScreencount));
	String text3 = objLogin_Screennn.clkbuttonLogIn_445819();
		if(text3.contains(configFileObj.getProperty("LogIn"+ i + datasetScreencount))){
			printSuccessLogAndReport(logger,  "Clicked on Button : " + configFileObj.getProperty("LogIn"+ i + datasetScreencount));
			printSuccessLogAndReport(text3,  logger,  "Validated Button Text : " + configFileObj.getProperty("LogIn"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text3,  logger,  "Text is displayed as : " + configFileObj.getProperty("LogIn"+ i + datasetScreencount));
		}

	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport( logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	

	public void DemoScreenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 2;
		DemoScreen objDemoScreen = PageFactory.initElements(driver, DemoScreen.class);
		testLogHeader("Verify DemoScreen page");
		objDemoScreen.fillinputPassword_446794(configFileObj.getProperty("Password"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Password : " + configFileObj.getProperty("Password"+ i + datasetScreencount));
		objDemoScreen.textdivStillNeedHelpCallUsAt8885068536OrEmailUsAt_446799();
		printInfoLogAndReport( logger, "Label text value is:  StillNeedHelpCallUsAt8885068536OrEmailUsAt");
		objDemoScreen.textlabelPassword_446800();
		printInfoLogAndReport( logger, "Label text value is:  Password");
		objDemoScreen.clkimgNotifii_446801();
		printSuccessLogAndReport( logger,  "Text is displayed as : NotifiiLink");

	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport( logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	

	public void Package_history_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 3;
		Package_history_screen objPackage_history_screen = PageFactory.initElements(driver, Package_history_screen.class);
		testLogHeader("Verify Package_history_screen page");
		objPackage_history_screen.clkaPackages_422454();
		printSuccessLogAndReport( logger,  "Text is displayed as : PackagesLink");
		objPackage_history_screen.clkaPackageHistory_422455();
		printSuccessLogAndReport( logger,  "Text is displayed as : PackageHistoryLink");
		objPackage_history_screen.clkbuttonUpdatePackageHistory_422413();
		printInfoLogAndReport(logger, "Clicked on UpdatePackageHistoryButton");
		
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
			setTestcaseName(browserName,"TC_Demo_Testcase - " + configFileObj.getProperty("dataset" + (datasets)));
			if(isElementDispalyed) { Login_ScreennnTest(datasets);}
			if(isElementDispalyed) { DemoScreenTest(datasets);}
			if(isElementDispalyed) { Package_history_screenTest(datasets);}
			tearDown();
		}	}

	
	public void tearDown() throws Exception {
		driver.quit();
	}
}