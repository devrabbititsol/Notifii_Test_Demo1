package api.test_notifii_demo.api_demo.apitestclasses;

import com.restassured.services.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;
import com.configurations.*;
import api.test_notifii_demo.api_demo.apipageclasses.API_POSTDataset;
import api.test_notifii_demo.api_demo.apipageclasses.API_GETDataset;
import api.test_notifii_demo.api_demo.apipageclasses.API_PUTDataset;
public class TC_CRUD_API extends BaseClass {
	ExtentReports reports;
	ExtentTest test;
	ITestResult result;
	private Logger logger;
	
	String[] datasetNames = new String[] {"Default"};
	private ConfigFilesUtility configFileObj;
	String projectPath = System.getProperty("user.dir");
	String reportsPath = projectPath + "/APIReports/" + ReportPaths.reportPathName;

	public TC_CRUD_API() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TC_CRUD_API.class);
		configFileObj= new ConfigFilesUtility();
		configFileObj.loadPropertyFile("apiconfig.properties");
	}

	@Test(priority = 1)
	public void doRequest() throws Exception { 

		String primaryInfo = API_PUTDataset.primaryInfo;
		Constants.PRIMARY_INFO = primaryInfo;
		reports = ExtentConfigurations.getExtentInstance(reportsPath, projectPath ,API_PUTDataset.projectName);
		int datasetLength = API_PUTDataset.datasetLength; 
		for(int i = 1; i <= datasetLength; i++) {
		test = reports.startTest("TC_CRUD_API - " + datasetNames[i-1]);

			boolean isExecutionFlag = true;			
		/*--------------------- POST --------------------- */

			if(isExecutionFlag) {
 			 	Object object = (Object) new API_POSTDataset();
				Class<?> c = object.getClass();
				String urlParams = (String) c.getField("urlParams" + i).get(object);
				if(urlParams == null) return;
				String headers = (String) c.getField("headers" + i).get(object);
				String body = (String) c.getField("body" + i).get(object);
				String datasetHeader = (String) c.getField("datasetHeader" + i).get(object);
				String datasetResources = (String) c.getField("datasetResources" + i).get(object);
				int reqType = (Integer) c.getField("requestType" + i).get(object);
				int bodyType = (Integer) c.getField("bodyType" + i).get(object);
			 	Constants.testName = Constants.testName + datasetHeader;
				String response = APIService.callRequest(primaryInfo, urlParams, headers, reqType, bodyType, body, datasetHeader, datasetResources, test, logger);
			}

		/*--------------------- GET --------------------- */

			if(isExecutionFlag) {
 			 	Object object = (Object) new API_GETDataset();
				Class<?> c = object.getClass();
				String urlParams = (String) c.getField("urlParams" + i).get(object);
				if(urlParams == null) return;
				String headers = (String) c.getField("headers" + i).get(object);
				String body = (String) c.getField("body" + i).get(object);
				String datasetHeader = (String) c.getField("datasetHeader" + i).get(object);
				String datasetResources = (String) c.getField("datasetResources" + i).get(object);
				int reqType = (Integer) c.getField("requestType" + i).get(object);
				int bodyType = (Integer) c.getField("bodyType" + i).get(object);
			 	Constants.testName = Constants.testName + datasetHeader;
				String response = APIService.callRequest(primaryInfo, urlParams, headers, reqType, bodyType, body, datasetHeader, datasetResources, test, logger);
			}

		/*--------------------- PUT --------------------- */

			if(isExecutionFlag) {
 			 	Object object = (Object) new API_PUTDataset();
				Class<?> c = object.getClass();
				String urlParams = (String) c.getField("urlParams" + i).get(object);
				if(urlParams == null) return;
				String headers = (String) c.getField("headers" + i).get(object);
				String body = (String) c.getField("body" + i).get(object);
				String datasetHeader = (String) c.getField("datasetHeader" + i).get(object);
				String datasetResources = (String) c.getField("datasetResources" + i).get(object);
				int reqType = (Integer) c.getField("requestType" + i).get(object);
				int bodyType = (Integer) c.getField("bodyType" + i).get(object);
			 	Constants.testName = Constants.testName + datasetHeader;
				String response = APIService.callRequest(primaryInfo, urlParams, headers, reqType, bodyType, body, datasetHeader, datasetResources, test, logger);
			}
		}
	}

	@AfterClass
	public void tearDown() throws Exception {
		reports.endTest(test);
		reports.flush();
	}
}