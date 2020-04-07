package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends PageObject {
	

	// Initiation of all web elements as variables
	private @FindBy (how = How.XPATH, using="//input[@id='txtUsername']") WebElement userName;
	
	private @FindBy (how = How.ID, using = "txtPassword") WebElement password;
	
	private @FindBy (how = How.XPATH, using = "//input[@id='btnLogin']") WebElement loginButton;
	
	private @FindBy (how = How.XPATH, using = "//a[contains(text(),'Forgot')]") WebElement forgotPasswordLink;
	
	private @FindBy (how = How.XPATH, using = "//span[@id='spanMessage']") WebElement invalidCredentials;
	
	
	// Constructor with the driver assigning to the parent
	public LoginPage(WebDriver driver)
	{
		
		super(driver);
	}
	
	// Method to login to the application
	public void loginToHomePage(String uName, String pWord)
	{
		// Set the value of both username, password and click the button to login
		setValue(userName,uName);
		setValue(password,pWord);
		loginButton.click();
		waitForBrowserStability();
		
	}
	
	// Method to click the forgot password link
	public void clickForgotPassword()
	{
		
		forgotPasswordLink.click();
		waitForBrowserStability();
		
	}
	
	// Method to check whether the label is present if invalid credentials are provided
	public boolean isLabelPresent(String label)
	{
		
		String labelValue = invalidCredentials.getText();
		if (labelValue.equalsIgnoreCase(labelValue))
			return true;
		else
			return false;		
	}

}
