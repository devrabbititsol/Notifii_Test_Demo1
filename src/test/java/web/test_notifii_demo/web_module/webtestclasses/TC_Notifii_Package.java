package web.test_notifii_demo.web_module.webtestclasses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
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
public class TC_Notifii_Package extends BaseClass {
	private Logger logger;
	private ConfigFilesUtility configFileObj;
	private String browserName = "chrome";
	public boolean isElementDispalyed = false;


	public TC_Notifii_Package() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TC_Notifii_Package.class);
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("tc_notifii_package.properties");
	}

	@BeforeTest
	@Parameters("browser")
	public void browserName(String browser) throws Exception {
		browserName = browser;
	}

	
	public void setUP() throws Exception {
		driver = launchBrowser(browserName, configFileObj);
	}

	public void Login_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 1;
		Login_screen objLogin_screen = PageFactory.initElements(driver, Login_screen.class);
		testLogHeader("Verify Login_screen page");
		objLogin_screen.fillinputUsername_422212(configFileObj.getProperty("Username"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Username : " + configFileObj.getProperty("Username"+ i + datasetScreencount));
		objLogin_screen.fillinputPassword_422216(configFileObj.getProperty("Password"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Password : " + configFileObj.getProperty("Password"+ i + datasetScreencount));
		objLogin_screen.clkbuttonLogIn_422225();
		printInfoLogAndReport(logger, "Clicked on LogInButton");
		
	   } catch (Exception e) {
		  isElementDispalyed = false;
		  printFailureLogAndReport( logger,  "Element is not found" + e.getLocalizedMessage());
		}
	}
	

	public void Home_screenTest(int i) throws Exception {

	 try{
		int datasetScreencount = 2;
		Home_screen objHome_screen = PageFactory.initElements(driver, Home_screen.class);
		testLogHeader("Verify Home_screen page");
		objHome_screen.filltextareaTracking_number_422230(configFileObj.getProperty("Tracking_number"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Tracking_number : " + configFileObj.getProperty("Tracking_number"+ i + datasetScreencount));
		objHome_screen.clkspanSearch_422279();
		printSuccessLogAndReport( logger,  "Text is displayed as : SearchLink");
		objHome_screen.fillinputSelect2results1_422235(configFileObj.getProperty("Select2results1"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Select2results1 : " + configFileObj.getProperty("Select2results1"+ i + datasetScreencount));
		objHome_screen.clkDIVSelect_name_422543();
		printSuccessLogAndReport( logger,  "Text is displayed as : Select_nameLink");
		objHome_screen.filltextareaCustom_message_422238(configFileObj.getProperty("Custom_message"+ i + datasetScreencount));
		printSuccessLogAndReport(logger,  "Entered Custom_message : " + configFileObj.getProperty("Custom_message"+ i + datasetScreencount));
		objHome_screen.clkbuttonLogThisPackageIn_422314();
		printInfoLogAndReport(logger, "Clicked on LogThisPackageInButton");
		
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
		String text2 = objPackage_history_screen.clkaPackageHistory_422455();
		if(text2.contains(configFileObj.getProperty("PackageHistory"+ i + datasetScreencount))){
			printSuccessLogAndReport(text2,  logger,  "Clicked on : " + configFileObj.getProperty("PackageHistory"+ i + datasetScreencount));
			printSuccessLogAndReport(text2,  logger,  "Validated Link Text : " + configFileObj.getProperty("PackageHistory"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text2,  logger,  "Link Text is not displayed  : " + configFileObj.getProperty("PackageHistory"+ i + datasetScreencount));
		}
	String text3 = objPackage_history_screen.clkbuttonUpdatePackageHistory_422413();
		if(text3.contains(configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount))){
			printSuccessLogAndReport(logger,  "Clicked on Button : " + configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount));
			printSuccessLogAndReport(text3,  logger,  "Validated Button Text : " + configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount));
		} else {
			printFailureLogAndReport(text3,  logger,  "Text is displayed as : " + configFileObj.getProperty("UpdatePackageHistory"+ i + datasetScreencount));
		}

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
			setTestcaseName(browserName,"TC_Notifii_Package - " + configFileObj.getProperty("dataset" + (datasets)));
			Login_screenTest(datasets);
			Home_screenTest(datasets);
			Package_history_screenTest(datasets);
			tearDown();
		}	}

	
	public void tearDown() throws Exception {
		driver.quit();
	}
}