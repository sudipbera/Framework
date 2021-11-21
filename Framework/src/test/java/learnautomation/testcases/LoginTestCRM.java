package learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import learnautomation.pages.BaseClass;
import learnautomation.pages.LoginPage;
import learnautomation.utilities.BrowserDetails;
import learnautomation.utilities.ExcelDataProvider;
import learnautomation.utilities.Helper;

public class LoginTestCRM extends BaseClass{

	

	@Test(priority=1)
	public void loginApp()
	{
		//Test case name
		logger=report.createTest("Login to CRM");
		
		//ExcelDataProvider excel = new ExcelDataProvider();
		//excel.getStringData("Login", 0, 0);
		
		//System.out.println(driver.getTitle());
		//once all done then it will return the object of LoginPage class
		LoginPage loginpage=PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting Application");
		
		//loginToCRM method here is Abstruction - showing functionality and hiding the implementation(description in other class)
		loginpage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		
		logger.pass("Login Success");
		
		//Helper.captureScreenshot(driver);
	}
	


}
