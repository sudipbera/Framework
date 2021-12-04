package learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import learnautomation.utilities.BrowserDetails;
import learnautomation.utilities.ConfigDataProvider;
import learnautomation.utilities.ExcelDataProvider;
import learnautomation.utilities.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite() {
		
		Reporter.log("Setting up reports and Test is getting ready", true);
		
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html"));
		// create object of extent report
		report = new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting Done- Test can be started", true);

	}
	
	@Parameters({"Browser","urlToBeTested"})
	@BeforeClass
	public void setup(String Browser,String url) {
		
		Reporter.log("Trying to start Browser and getting application ready", true);
		
		//driver = BrowserDetails.startApplication(driver, config.getBrowser(), config.getStagingURL());
		driver = BrowserDetails.startApplication(driver, Browser, url);
		
		Reporter.log("Browser and Application is up and running", true);
	}

	@AfterClass
	public void tearDown() {
		BrowserDetails.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		
		Reporter.log("Test is about to end", true);
		
		if (result.getStatus() == ITestResult.FAILURE) {
			// Helper.captureScreenshot(driver);

			logger.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		}

		// If 5 test cases are there it will keep on add the test cases in
		// single report
		report.flush();
		
		Reporter.log("Test Completed >>> Reports Generated", true);
	}
}
