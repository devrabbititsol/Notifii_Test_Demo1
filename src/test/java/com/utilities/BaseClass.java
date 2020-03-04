package com.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.configurations.GlobalData;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import groovy.json.StringEscapeUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BaseClass {
	public WebDriver driver;
	public WiniumDriver winiumDriver;
	private static boolean isElementDispalyed;
	private String driversPath = System.getProperty("user.dir") + File.separator + "Resources" + File.separator;
	private String chromeDriverPath = driversPath + "chromedriver.exe";
	private String geckoFireFoxDriverPath = driversPath + "geckodriver.exe";
	private String iEDriverPath = driversPath + "IEDriverServer.exe";
	public AppiumDriver<MobileElement> appiumDriver;
	public String text = "";
	
	// Explicit wait method
	public static WebElement waitForExpectedElement(WebDriver driver, final By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForExpectedElement(WebDriver driver, WebElement element) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("");
		}
		
		//Actions action = new Actions(driver);
		//action.moveToElement(element).perform();
		//isElementDispalyed = element.isDisplayed();
		initialInputDataClear(element); // if any text in input it will clear
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOf(element));
	
	}

	public static String initialInputDataClear(WebElement webElement) {
		String str = webElement.toString();
		try {
			if (str != null && str.toUpperCase().contains("INPUT")) {
			String[] listString = null;
			if (str.contains("xpath")) {
				listString = str.split("xpath:");
			} else if (str.contains("id")) {
				listString = str.split("id:");
			}
			String last = listString[1].trim();
			String xpath = last.substring(0, last.length() - 1);
			if (xpath != null && str.toUpperCase().contains("INPUT")) {
				webElement.clear();
			}
			}
		} catch (Exception e) {
			//System.out.println("Not editable input");
		}
		return str;
	}

	// Explicit wait
	public static WebElement waitForExpectedElement(WebDriver driver, final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Explicit wait method
	public boolean objectExists(WebDriver driver, final By locator) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Method for Retry and It executes the failed test case based on our count
	public class Retry implements IRetryAnalyzer {
		private int count = 0;
		private static final int maxTry = 3;

		public boolean retry(ITestResult iTestResult) {
			if (!iTestResult.isSuccess()) { // Check if test not succeed
				if (count < maxTry) { // Check if maxtry count is reached
					count++; // Increase the maxTry count by 1
					iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
					return true; // Tells TestNG to re-run the test
				} else {
					iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
				}
			} else {
				iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
			}
			return false;
		}
	}

	// Explicit wait method (While Script Execution we need to pass time limit)
	public boolean objectExists(WebDriver driver, final By locator, int timeout) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Explicit wait method (While Script Execution we need to pass time limit
	public void waitForPageToLoad() {
		(new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete"));
			}
		});
	}
	
	/**
	 * Driver instance for Desktop Applications
	 * 
	 * @param applicationexePath
	 * @param remoteWiniumDriverPath
	 * @return
	 * @throws Exception
	 */
	public WiniumDriver launchDesktopApp(String applicationexePath, String remoteWiniumDriverPath) throws Exception {
		DesktopOptions options = new DesktopOptions(); 
		options.setApplicationPath(applicationexePath); 
		winiumDriver = new WiniumDriver(new URL(remoteWiniumDriverPath), options);
		return winiumDriver;
	}

	
	@SuppressWarnings("deprecation")
	public WebDriver launchBrowser(String browserName, ConfigFilesUtility configFileObj) {
		
		GlobalData.primaryInfoData(configFileObj);
		if (!isWindows()) {
			if (isSolaris() || isUnix()) {
				chromeDriverPath = chromeDriverPath.replace(".exe", "");
				geckoFireFoxDriverPath = geckoFireFoxDriverPath.replace(".exe", "");
			} else if (isMac()) {
				chromeDriverPath = chromeDriverPath.replace("chromedriver.exe", "macChromeDriver");
				geckoFireFoxDriverPath = geckoFireFoxDriverPath.replace("geckodriver.exe", "macGeckodriver");
			}
		}

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs=new HashMap<String,Object>();
			//1-Allow, 2-Block, 0-default
			prefs.put("profile.default_content_setting_values.notifications", 1);
			options.setExperimentalOption("prefs",prefs);
			
			if (isSolaris() || isUnix()) {
				
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
				options.addArguments("--no-sandbox"); // Bypass OS security model
				options.addArguments("--headless"); // this line makes run in linux environment with jenkins
				driver = new ChromeDriver(options);
			} else {
				driver = new ChromeDriver(options);
			}

			System.out.println("Chrome Browser is Launched");
		} else if (browserName.equalsIgnoreCase("mozilla")) {
			System.setProperty("webdriver.gecko.driver", geckoFireFoxDriverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//			FirefoxProfile profile = new FirefoxProfile();
//			//1-Allow, 2-Block, 0-default
//			profile.setPreference("permissions.default.desktop-notification", 1);
//			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			
			if (isSolaris() || isUnix()) {
				FirefoxBinary binary = new FirefoxBinary();
				
				capabilities.setCapability("marionette", true);
				FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
				firefoxOptions.setBinary(binary);
			
				firefoxOptions.addArguments("--no-sandbox"); // Bypass OS security model
				firefoxOptions.addArguments("--headless"); 
				driver = new FirefoxDriver(firefoxOptions);
			} else {
				FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
				driver = new FirefoxDriver(firefoxOptions);
			//	driver.manage().window().setPosition(new Point(-2000, 0));
			}
			
			System.out.println("FireFox Browser is Launched");
		} else if (browserName.equalsIgnoreCase("safari")) {
			// Note : Should AllowRemoteAutomation in safari browser DeveloperMenu
			// Directions -- > launchSafariBrowser --> Preferences --> Advanced Tab -->
			// Show Developer Menu --> Click on DevloperMenu --> Enable
			// AllowRemoteAutomation
			// System.setProperty("webdriver.safari.noinstall", "true");
			driver = new SafariDriver();
			//driver.get("http://www.google.com");
			System.out.println("Safari Browser is Launched");
		} else if (browserName.equalsIgnoreCase("ie")) {
			// To run Internet explorer you should enable below configuration in IE
			// Internet Explorer -> Settings -> Security tab -> Enable Protected mode in all zones 
			
			if (!isWindows()) {
				System.out.println("IE Browser not supported for this OS.");
				return null;
			}
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", iEDriverPath);
			driver = new InternetExplorerDriver(capabilities);
			System.out.println("IE Browser is Launched");
		}
		
		driver.get(configFileObj.getProperty("URL"));
		if (isSolaris() || isUnix()) {
			Dimension d = new Dimension(1382, 744);
			// Resize the current window to the given dimension
			driver.manage().window().setSize(d);
		} else {
			java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;

			Dimension d = new Dimension(screenWidth, screenHeight);
			// Resize the current window to the given dimension
			driver.manage().window().setSize(d);
			//driver.manage().window().setPosition(new Point(-2000, 0));
		}
		return driver;
	}

	private String OS = System.getProperty("os.name").toLowerCase();

	public boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}


	// =================================================New style Reports========================================================
	
	//===================== For Report ========================
	JSONArray jsonArray;

	//For Web
	public void setTestcaseName(String browserName, String tescaseName) {
		String chromeURL = "chrome";
		String mozillaURL = "mozilla";
		String ieURL = "ie";
		String safariURL = "safari";
		String finalURL = "";
		if (browserName.equalsIgnoreCase("chrome")) {
			finalURL = chromeURL;
		} else if (browserName.equalsIgnoreCase("mozilla")) {
			finalURL = mozillaURL;
		} else if (browserName.equalsIgnoreCase("ie")) {
			finalURL = ieURL;
		} else if (browserName.equalsIgnoreCase("safari")) {
			finalURL = safariURL;
		}
		JSONObject jsonoBj = new JSONObject();
		jsonArray = new JSONArray();
		jsonoBj.put("browser_type",finalURL);
		jsonoBj.put("testcase_name",tescaseName);
		jsonoBj.put("datasets", jsonArray);
		GlobalData.reportData(tescaseName, jsonoBj);
		// testcaseObj.put(tescaseName, jsonArray);
	}
	
	
	

	public void testLogHeader(String data) {
		reportHeadersCreation("info", data);
	}

	public void printSuccessLogAndReport(Logger logger, String data) {

		reportCreation("pass", data);

		try {
			if (data.contains("satyanarayana bolli")) {

				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(200);
				}
			if (data.contains("8142243634") || data.contains("9332") || data.contains("Ranga Swamy")) {

				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(200);
			}
		} catch (Exception e) {
			System.out.println("");
		}

		if (logger != null)
			logger.info(data);
	}

	public void printSuccessLogAndReport(String elementText, Logger logger, String data) {
		if (data.contains("printMsg")) {
			reportCreation("info", elementText);
		} else {
			reportCreation("pass", data);
		}
		if (logger != null)
			logger.info(elementText);
	}

	public void printFailureLogAndReport(String elementText, Logger logger, String data) {

		if (data.contains("printMsg")) {
			reportCreation("info", elementText);
		} else {
			String name = "";
			if (data.toString().length() <= 20) {
				name = data.toString();
			} else {
				name = data.toString().substring(0, 10);
			}
			reportFailureCreation("fail", StringEscapeUtils.escapeJava(data),Utilities.captureScreenshot(driver, name));
		}
		if (logger != null)
			logger.error(data);
	}

	public void printFailureLogAndReport(Logger logger, String data) {
		if(data == null) data = "";
		if (logger != null)
			logger.error(data);
		String name = "";
		if (data.toString().length() <= 20) {
			name = data.toString();
		} else {
			name = data.toString().substring(0, 10);
		}
		reportFailureCreation("fail", StringEscapeUtils.escapeJava(data), Utilities.captureScreenshot(driver, name));
	}

	/***
	 * Fail status for Desktop Application Reoport
	 * 
	 * @param test
	 * @param logger
	 * @param data
	 */

	public void printFailureLogAndReportDesktop(Logger logger, String data) {
		if (logger != null)
			logger.error(data);
		String name = "";

		if (data.toString().length() <= 20) {
			name = data.toString();
		} else {
			name = data.toString().substring(0, 10);
		}
		reportFailureCreation("fail", StringEscapeUtils.escapeJava(data),Utilities.captureScreenshotDesktopApplication(winiumDriver, name));

	}

	public void printInfoLogAndReport(Logger logger, String data) {
		logger.info(data);
		reportCreation("info", data);
		//GlobalData.primaryInfoData(conObj);
	}

	
	public void reportHeadersCreation(String result, String data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result_type", "screen");
		jsonObject.put("text", data);
		jsonArray.put(jsonObject);
		//GlobalData.primaryInfoData(conObj);
	}
	
	
	public void reportCreation(String result, String data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result_type", result);
		jsonObject.put("text", data);
		jsonArray.put(jsonObject);
		//GlobalData.primaryInfoData(conObj);
	}

	public void reportFailureCreation(String result, String data, String image) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result_type", result);
		jsonObject.put("text", data);
		jsonObject.put("screenshot", image);
		jsonArray.put(jsonObject);
	}
	
	//============= End Report ===============
	
	public void tearDown(ExtentReports reports, ExtentTest test) throws Exception {
		reports.endTest(test);
		reports.flush();
		driver.quit();
	}

	// mouseHover
	public void mouseHover(WebDriver webDriver, WebElement element) {

		Actions action = new Actions(webDriver);
		action.moveToElement(element).build().perform();
		
		// action.moveToElement(we).moveToElement(driver.findElement(By.xpath(elementClickXpath))).click().build().perform();
	}

	// window
	String parentHandle = "";
	public void windowHandle(WebDriver webDriver) {
		parentHandle = webDriver.getWindowHandle();
		Set<String> handles = webDriver.getWindowHandles();
		for (String windowHandles : handles) {
			System.out.println(windowHandles);
			webDriver.switchTo().window(windowHandles);
		}
	}

	public void switchToParentWindow1(WebDriver webDriver) {
		if (parentHandle != null && !parentHandle.isEmpty()) {
			webDriver.switchTo().window(parentHandle);
		}
	}

	// upload a file
	public void uploadFile(String path, String xpath) {
		/*try {	
			WebElement element = waitForExpectedElement(driver,By.xpath(xpath));	
			element.sendKeys(name);
		} catch (Exception e) {
			e.getMessage();
		}*/
		
		try {
	        //Setting clipboard with file location
			StringSelection selection = new StringSelection(path);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
	        //native key strokes for CTRL, V and ENTER keys
	        Robot robot = new Robot();

	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	    } catch (Exception exp) {
	        exp.printStackTrace();
	    }
	}

	// Dropdown
	public String dropDownHandle(String xpath, String optionName) {
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByValue(optionName);
		WebElement option = oSelect.getFirstSelectedOption();
		option.getText();
		return "";
	}
	
	//handle table validations
	public String tableDataHandle(ExtentTest test,String xpath) {
		List<WebElement> rows;
		if(xpath.startsWith("/")) {
			waitForExpectedElement(driver, By.xpath(xpath));
			xpath = xpath + "/tbody/tr";
			rows = driver.findElements(By.xpath(xpath));
		} else {
			waitForExpectedElement(driver, By.cssSelector(xpath));
			xpath = xpath + ">tbody>tr";
			rows = driver.findElements(By.cssSelector(xpath));
		}
		//List<WebElement> rows = element.findElements(By.xpath(trXpath));
		//Print data from each row
		test.log(LogStatus.INFO, "Number of rows : " + rows.size());
		for (WebElement row : rows) {
			  System.out.print(row + "\t");
		    List<WebElement> cols = row.findElements(By.tagName("td"));
		    for (WebElement col : cols) {
		        System.out.print(col.getText() + "\t");
		    }
		    System.out.println();
		}
		return "" + rows.size();

	}
	
	// Switching to iframe by position
	public void switchToIframe(String xpath) {
		try {
			Thread.sleep(1000);
			for (int framePosition = 0; framePosition <= 5; framePosition++) {
				try {
					driver.switchTo().frame(framePosition);
					WebElement ele;
					if(xpath.startsWith("/")) {
					 ele = driver.findElement(By.xpath(xpath));
					} else {
					 ele = driver.findElement(By.cssSelector(xpath));
					}
					if (ele.isDisplayed()) {
						//driver.switchTo().frame(framePosition);
						System.out.println(framePosition);
						break;
					}
				} catch (Exception e) {
					driver.switchTo().parentFrame();
				}
			}
		} catch (Exception e) {
			driver.switchTo().parentFrame();
		}
	}
	
	
	public void alertHandle(boolean isAlertAccept) {
		try {
			Alert alert = driver.switchTo().alert();
			String alertMessage = driver.switchTo().alert().getText();
			System.out.println(alertMessage);
			Thread.sleep(5000);
			if (isAlertAccept) {
				alert.accept();
			} else {
				alert.dismiss();
			}
		} catch (Exception e) {

		}
	}
	
	
	public boolean isElementPresent(WebElement e) {
	    try {
	        boolean isDisplayed = e.isDisplayed();
	        isElementDispalyed = isDisplayed;
	    } catch (Exception exception) {
	        isElementDispalyed = false;      
	    }
	    return isElementDispalyed;
	}
	
	public boolean skipifElementisNotDisplayed() {
		return isElementDispalyed;
	}
	
	
}
