package learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	//The @FindBy annotation is used in Page Objects in Selenium tests 
	//to specify the object location strategy for a WebElement or a list of WebElements.
	//FindBy is an annotation while findElement and findElements are methods
	
	//FindBy is used to support PageObject pattern through PageFactory 
	//while findElement is normal way of locating a web element.
	//If we do not initialise page objects using PageFactory, 
	//@FindBy will throw NullPointerException which is not the case with findElement() or findElements() methods.
	//FindBy will look for the element only when it is used while findElement will look for an element as soon as you call it.
	
	@FindBy(xpath="//span[contains(text(),'Log In')]") WebElement SignIn;
	
	
	@FindBy(name="email") WebElement username;
	
	@FindBy(name="password") WebElement password;
	
	@FindBy(xpath="//div[contains(text(),'Login')]") WebElement login;
	
	public void loginToCRM(String usernameApplication,String passwordApplication)
	{
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			
		}
		SignIn.click();
		username.sendKeys(usernameApplication);
		password.sendKeys(passwordApplication);
		login.click();
		
	}
}
