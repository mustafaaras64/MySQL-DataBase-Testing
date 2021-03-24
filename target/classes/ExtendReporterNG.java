package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

	static ExtentReports extent;				
	public static ExtentReports getReportObject() {				
					
		String path =System.getProperty("user.dir")+"\\reports\\index.html";			
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);			
		reporter.config().setReportName("H&M.com Web Automation Results");			
		reporter.config().setDocumentTitle("Test Results");			
		extent= new ExtentReports();			
		extent.attachReporter(reporter);			
		extent.setSystemInfo("QA Tester&Engineer", "Mustafa Aras");			
					
		return extent;					
	}				
}
