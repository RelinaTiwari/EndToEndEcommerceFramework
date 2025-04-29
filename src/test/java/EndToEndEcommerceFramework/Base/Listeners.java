package EndToEndEcommerceFramework.Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import EndToEndEcommerceFramework.resources.ExtentReporterNG;

public class Listeners extends BaseClass implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
			
	@Override
	public void onTestStart(ITestResult result) {
		//This test is not thread safe , so in case of parallel execution it will override test instance and provide irrelevent SS
		//SO for that we need ot make it thread sfe 
		//By providing threadlocal
		//test = extent.createTest(result.getMethod().getMethodName());
		
		test = extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test); // unique thread id of each testcase being executed
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extenttest.get().log(Status.FAIL, result.getThrowable());
		String path = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			path = getScreenShot(result.getMethod().getMethodName() , driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extenttest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
