package com.fileupload;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;

import com.configurations.GlobalData;
import com.restassured.services.ReportPaths;
import com.utilities.BaseClass;
import com.utilities.Utilities;

@SuppressWarnings("unused")
public class ReportUpload extends BaseClass {
	String primaryInfo = "";
	private String projectPath = System.getProperty("user.dir");
	private String reportsPath = projectPath + File.separator + "APIReports" + File.separator + ReportPaths.reportPathName;
	private String resultCount;
	private String datasetResult;
	private String reportstatus;


	/*
	 * Uploading file to server
	 * @moduleId 
	 * @testCaseId
	 * @userID
	 * @testsetId
	 * @moduleDescription
	 */
	

	public void call() {
		System.out.println(GlobalData.getReportData());
		System.out.println("PrimaryInfo:" + GlobalData.getPrimaryInfo());
	}
	
	@AfterSuite
	public void uploadReport() throws Exception {
	
		try {
			System.out.println(GlobalData.getReportData());
			JSONObject primaryInfoObj = new JSONObject(GlobalData.getPrimaryInfo());
			boolean is_web = primaryInfoObj.optBoolean("is_web");
			boolean isDesktopAutomation = primaryInfoObj.optBoolean("is_Desktop_Automation");
			String mobile = primaryInfoObj.optString("mobile_platform");
			String moduleDescription = primaryInfoObj.optString("module_description");
			//System.err.println(Constants.testName);
			String moduleId = primaryInfoObj.optString("module_id");
			String userId = primaryInfoObj.optString("user_id");
			String executedUserId = primaryInfoObj.optString("executed_user_id");
			long startExecutionTime = GlobalData.getStartTime();
			long endExecutionTime = GlobalData.getEndTime();
			String client_timezoneId = primaryInfoObj.optString("client_timezone_id");
			String report_upload_url = primaryInfoObj.optString("report_upload_url");
			String testcaseId = primaryInfoObj.optString("testcase_id");
			String datasetId = primaryInfoObj.optString("testcase_id");
			String subModuleId = primaryInfoObj.isNull("sub_module_id") ? null : primaryInfoObj.optString("sub_module_id");
			String testsetId = primaryInfoObj.optString("testset_id").equals("0") ? "" : primaryInfoObj.optString("testset_id");
			String testsetName = (primaryInfoObj.optString("testset_name") == null || primaryInfoObj.optString("testset_name").equals("null") || primaryInfoObj.optString("testset_name").isEmpty()) ? "" : primaryInfoObj.optString("testset_name");
			JSONArray testcasesArray = GlobalData.getReportData();
			JSONObject primaryInfo = new JSONObject();
			primaryInfo.put("testset_name", testsetName);
			//String elapsedTime = Utilities.getElapsedTime(reportPath);
			//MultipartUtility multipart = new MultipartUtility(reportUploadURL, charset);
			primaryInfo.put("user_id", userId);
			primaryInfo.put("executed_user_id", executedUserId);
			if (!testsetId.isEmpty()) {
				primaryInfo.put("testset_id", testsetId);
			} else {
				primaryInfo.put("testcase_id", testcaseId);
			}
			 
			if(mobile != null && (mobile.equalsIgnoreCase("Android")||mobile.equalsIgnoreCase("iOS"))) {
				primaryInfo.put("report_type", mobile);
			} else if(isDesktopAutomation) {
				primaryInfo.put("report_type", "winium");
			} else {
				primaryInfo.put("report_type", is_web ? "web" : "api");
			}
			
			primaryInfo.put("module_id", (subModuleId == null || subModuleId.isEmpty() || subModuleId.equals("0")) ? moduleId : subModuleId);
			//primaryInfo.put("report_result", result);
			//primaryInfo.put("dataset_result", datasetResult);
			primaryInfo.put("start_time", String.valueOf(startExecutionTime));
			primaryInfo.put("end_time", String.valueOf(endExecutionTime));
			
			long elapsedtime = startExecutionTime- endExecutionTime;
			primaryInfo.put("execution_time", String.valueOf(elapsedtime));
			//primaryInfo.put("report_status", reportstatus);
			primaryInfo.put("client_time_zone_id", client_timezoneId);
			//System.out.println("client_time_zone_id"+  client_timezoneId);
			//primaryInfo.put("report_data", GlobalData.getReportData().toString());
			primaryInfo.put("testcases_result", testcasesArray);
			
			System.out.println(primaryInfo.toString());
			
			doSaveElementsToServer(report_upload_url, primaryInfo.toString());
			
			//new FileUploaderClient().uploadFile(report_upload_url, reportsPath, userId,executedUserId, testcaseId, testsetId, moduleId, subModuleId, is_web, resultCount, GlobalData.getReportData().toString(), mobile, client_timezoneId,datasetResult,false, startExecutionTime,endExecutionTime);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private boolean doSaveElementsToServer(String url, String json) throws Exception {
		try {
			URL obj = new URL(url);
			System.out.println(url);
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					// TODO Auto-generated method stub

				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(200000);
			con.setConnectTimeout(200000);
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			DataOutputStream wr = new DataOutputStream(os);
			byte[] isoString = json.getBytes("UTF-8");
			
			wr.write(isoString, 0, isoString.length);
			// wr.writeBytes(json);
			wr.flush();
			wr.close();
			os.close();
			con.connect();
			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());

			JSONObject jsonObject = new JSONObject(response.toString());
			if (jsonObject.has("status") && !jsonObject.getString("status").equalsIgnoreCase("SUCCESS")) {
				System.out.println("Report Exception " + jsonObject.optString("message"));
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		return true;
	}

}
