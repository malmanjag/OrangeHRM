package com.orangehrm.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.LandingPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.Utilities;


public class LoginProgressionTests extends SuperTestNG {

	static final Logger loggers = LogManager.getLogger(LoginProgressionTests.class);
	
	
	String testName = "";

	
	LoginPage login;
	LandingPage landingPage;
	Utilities utilityObject;
	
	
	// Test case to test the successful login and logout
	@Test(testName = "successfulLoginAndLogout", groups = {"successful"} )
	public void successfulLoginAndLogout() 
	{
		testName = new LoginProgressionTests() {}.getClass().getEnclosingMethod().getName();
		
		
		// Initiate the objects for both the page classes
		login = PageFactory.initElements(driver, LoginPage.class);
		landingPage = PageFactory.initElements(driver, LandingPage.class);
		
		
		loggers.info("This is the first logged information of message");
		
		// create the extent report for this test
		logger = extent.createTest(testName);
		
		// Initiate an object of Utilities class
		utilityObject = new Utilities(driver);
		
		// Login to the application by passing the valid credentials and wait for specified seconds
		screenShot = utilityObject.getScreenshot(testName);
		login.loginToHomePage("admin", "admin123");		
		//login.waitForSeconds(5);		
		
		screenShot = utilityObject.getScreenshot(testName);
		
		// Verification of successful landing page
		boolean isLogoPresent = landingPage.verifyLogo();
		
		
		// Check for logo present of the landing page and assert true if its present and false if it does not
		if(isLogoPresent)
		{
			Assert.assertTrue(isLogoPresent);
			landingPage.clickWelcomeLogin();
			landingPage.waitForSeconds(5);
			
			landingPage.clickLogout();
			landingPage.waitForSeconds(5);
			screenShot = utilityObject.getScreenshot(testName);
			
		}
		else
		{
			Assert.assertFalse(isLogoPresent);
		}		
		
	}

	// Test case to ensure the error is displayed if the credentials are entered wrongly for the login
	@Test(testName = "checkInvalidCredentials", priority = 1, groups = {"broken"} )
	public void checkInvalidCredentials() 
	{
		testName = new LoginProgressionTests() {}.getClass().getEnclosingMethod().getName();
		
		// Create the report for this test
		logger = extent.createTest(testName);
		
		// Initiate the objects for the LoginPage class and call the login method with invalid credentials
		login = PageFactory.initElements(driver, LoginPage.class);
		login.loginToHomePage("admin", "admin12");		
		//login.waitForSeconds(2);
		
		// Call a method to check whether the label is present or not to confirm its presence
		boolean labelPresent = login.isLabelPresent("Invalid Credentials");
		Assert.assertTrue(labelPresent);
		
	}

}
