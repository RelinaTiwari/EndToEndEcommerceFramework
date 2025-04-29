package EndToEndEcommerceFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporterNG {
	
	
	
	
	public static ExtentReports getReporterObject()
	{
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("Ecommerce Framework");
		extent.setSystemInfo("Tester", "Relina Tiwari");
		extent.attachReporter(reporter);
		return extent;
	}

}
