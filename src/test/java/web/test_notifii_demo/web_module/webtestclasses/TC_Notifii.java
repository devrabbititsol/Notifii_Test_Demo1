package web.test_notifii_demo.web_module.webtestclasses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.configurations.Constants;
import com.configurations.ExtentConfigurations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import web.test_notifii_demo.web_module.webpageclasses.Login_screen;
import web.test_notifii_demo.web_module.webpageclasses.Home_screen;
import web.test_notifii_demo.web_module.webpageclasses.Package_history_screen;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;
import com.utilities.Utilities;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class TC_Notifii extends BaseClass {
	ExtentReports reports;
	ExtentTest test;
	ITestResult result;
	private Logger logger;
	private ConfigFilesUtility configFileObj;
	private String browserName = "chrome";
	public boolean isElementDispalyed = false;
	public static final int datasetsLength = 1;
	String[] datasetNames = new String[] {"DS_regular"};

	public TC_Notifii() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TC_Notifii.class);
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("tc_notifii.properties");
	}

	@BeforeTest
	@Parameters("browser")
	public void browserName(String browser) throws Exception {
		browserName = browser;
		reports = ExtentConfigurations.getExtentInstance(reportsPath, projectPath, Login_screen.projectName);
		test = reports.startTest(setTestcaseName(browserName,"TC_Notifii - " + datasetNames[0]));
	}

	
	public void setUP() throws Exception {
		String primaryInfo = Login_screen.primaryInfo;
		Constants.PRIMARY_INFO = primaryInfo;
		driver = launchBrowser(browserName, configFileObj);
	}

	public void Login_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 1;
		Login_screen objLogin_screen = PageFactory.initElements(driver, Login_screen.class);
		testLogHeader(test, "Verify Login_screen page");
		objLogin_screen.fillinputUsername_422212(configFileObj.getProperty("Username"+ i + datasetScreencount));
		printSuccessLogAndReport(test, logger,  "Entered Username : " + configFileObj.getProperty("Username"+ i + datasetScreencount));
		objLogin_screen.fillinputPassword_422216(configFileObj.getProperty("Password"+ i + datasetScreencount));
		printSuccessLogAndReport(test, logger,  "Entered Password : " + configFileObj.getProperty("Password"+ i + datasetScreencount));
	String text3 = objLogin_screen.clkbuttonLogIn_422225();
		if(text3.equalsIgnoreCase(configFileObj.getProperty("LogIn"+ i + datasetScreencount))){
			printSuccessLogAndReport(test, logger,  "Clicked on Button : " + configFileObj.getProperty("LogIn"+ i + datasetScreencount));
			printSuccessLogAndReport(text3, test, logger,  "Validated Button Text : " + configFileObj.getProperty("LogIn"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text3, test, logger,  "Text is displayed as : " + configFileObj.getProperty("LogIn"+ i + datasetScreencount));
		}

	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport(test, logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	

	public void Home_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 2;
		Home_screen objHome_screen = PageFactory.initElements(driver, Home_screen.class);
		testLogHeader(test, "Verify Home_screen page");
		objHome_screen.filltextareaTracking_number_422230(configFileObj.getProperty("Tracking_number"+ i + datasetScreencount));
		printSuccessLogAndReport(test, logger,  "Entered Tracking_number : " + configFileObj.getProperty("Tracking_number"+ i + datasetScreencount));
		objHome_screen.clkspanSearch_422279();
		printSuccessLogAndReport(test, logger,  "Text is displayed as : SearchLink");
		objHome_screen.fillinputSelect2results1_422235(configFileObj.getProperty("Select2results1"+ i + datasetScreencount));
		printSuccessLogAndReport(test, logger,  "Entered Select2results1 : " + configFileObj.getProperty("Select2results1"+ i + datasetScreencount));
		objHome_screen.clkDIVSelect_name_422543();
		printSuccessLogAndReport(test, logger,  "Text is displayed as : Select_nameLink");
		objHome_screen.filltextareaCustom_message_422238(configFileObj.getProperty("Custom_message"+ i + datasetScreencount));
		printSuccessLogAndReport(test, logger,  "Entered Custom_message : " + configFileObj.getProperty("Custom_message"+ i + datasetScreencount));
	String text6 = objHome_screen.clkbuttonLogThisPackageIn_422314();
		if(text6.equalsIgnoreCase(configFileObj.getProperty("LogThisPackageIn"+ i + datasetScreencount))){
			printSuccessLogAndReport(test, logger,  "Clicked on Button : " + configFileObj.getProperty("LogThisPackageIn"+ i + datasetScreencount));
			printSuccessLogAndReport(text6, test, logger,  "Validated Button Text : " + configFileObj.getProperty("LogThisPackageIn"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text6, test, logger,  "Text is displayed as : " + configFileObj.getProperty("LogThisPackageIn"+ i + datasetScreencount));
		}

	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport(test, logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	

	public void Package_history_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 3;
		Package_history_screen objPackage_history_screen = PageFactory.initElements(driver, Package_history_screen.class);
		testLogHeader(test, "Verify Package_history_screen page");
		objPackage_history_screen.clkaPackages_422454();
		printSuccessLogAndReport(test, logger,  "Text is displayed as : PackagesLink");
		String text2 = objPackage_history_screen.clkaPackageHistory_422455();
		if(text2.equalsIgnoreCase(configFileObj.getProperty("PackageHistory"+ i + datasetScreencount))){
			printSuccessLogAndReport(test, logger,  "Clicked on : " + configFileObj.getProperty("PackageHistory"+ i + datasetScreencount));
			printSuccessLogAndReport(text2, test, logger,  "Validated Link Text : " + configFileObj.getProperty("PackageHistory"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text2, test, logger,  "Link Text is not displayed  : " + configFileObj.getProperty("PackageHistory"+ i + datasetScreencount));
		}
	String text3 = objPackage_history_screen.clkbuttonUpdatePackageHistory_422413();
		if(text3.equalsIgnoreCase(configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount))){
			printSuccessLogAndReport(test, logger,  "Clicked on Button : " + configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount));
			printSuccessLogAndReport(text3, test, logger,  "Validated Button Text : " + configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text3, test, logger,  "Text is displayed as : " + configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount));
		}

	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport(test, logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void screensTest() throws Exception {
		for(int datasets = 1; datasets <= Package_history_screen.datasetsLength; datasets++) {
			isElementDispalyed = true;			
			setUP();			
			if(datasets > 1) { test = reports.startTest(setTestcaseName(browserName,"TC_Notifii - " + datasetNames[datasets-1]));}
			if(isElementDispalyed) { Login_screenTest(datasets);}
			if(isElementDispalyed) { Home_screenTest(datasets);}
			if(isElementDispalyed) { Package_history_screenTest(datasets);}
			tearDown();
		}	}

	
	public void tearDown() throws Exception {
		reports.endTest(test);
		reports.flush();
		driver.quit();
	}
}