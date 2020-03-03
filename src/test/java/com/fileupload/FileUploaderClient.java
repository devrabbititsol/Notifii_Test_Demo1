package com.fileupload;

import java.util.List;

import com.utilities.Utilities;

public class FileUploaderClient {

	public void uploadFile(String reportUploadURL, String reportPath, String userId, String executedUserId, String testCaseId, String testSetID, String moduleId,
			String submoduleId, boolean is_web, String result, String reportData, String mobilePlatform, String client_timezoneId,String datasetResult,boolean isWinum, long startExecutionTime, long endExecutionTime) {

		String charset = "UTF-8";
		///String requestURL = "http://183.82.106.91:8030/BAF_Automation/UploadReportFile";

		try {
			//String elapsedTime = Utilities.getElapsedTime(reportPath);
			MultipartUtility multipart = new MultipartUtility(reportUploadURL, charset);
			multipart.addFormField("user_id", userId);
			multipart.addFormField("executed_user_id", executedUserId);
			if (!testSetID.isEmpty()) {
				multipart.addFormField("testset_id", testSetID);
			} else {
				multipart.addFormField("testcase_id", testCaseId);
			}
			
			if(mobilePlatform != null && (mobilePlatform.equalsIgnoreCase("Android")||mobilePlatform.equalsIgnoreCase("iOS"))) {
				multipart.addFormField("report_type", mobilePlatform);
			} else if(isWinum) {
				multipart.addFormField("report_type", "winium");
			} else {
				multipart.addFormField("report_type", is_web ? "web" : "api");
			}
			
			multipart.addFormField("module_id", (submoduleId == null || submoduleId.isEmpty() || submoduleId.equals("0")) ? moduleId : submoduleId);
			//multipart.addFormField("report_result", result);
			//multipart.addFormField("dataset_result", datasetResult);
			multipart.addFormField("start_time", String.valueOf(startExecutionTime));
			multipart.addFormField("end_time", String.valueOf(endExecutionTime));
			
			long elapsedtime = startExecutionTime- endExecutionTime;
			multipart.addFormField("execution_time", String.valueOf(elapsedtime));
			//multipart.addFormField("report_status", reportstatus);
			multipart.addFormField("client_time_zone_id", client_timezoneId);
			System.out.println("client_time_zone_id"+  client_timezoneId);
			multipart.addFormField("report_data", reportData);

			List<String> response = multipart.finish();

			//System.out.println("SERVER REPLIED:" + elapsedTime);

			for (String line : response) {
				System.out.println(line);
			}
			
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

}