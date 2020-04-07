package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LandingPage extends PageObject 
{
	
	// Initiation of all web elements as variables
	private @FindBy (how = How.XPATH, using = "//a[@id='welcome']") WebElement welcomeAdminLink;
	
	private @FindBy (how = How.XPATH, using = "//a[text()='Logout']") WebElement logoutlink;
	
	private @FindBy (how = How.XPATH, using = "//img[contains(@src,'logo.png')]") WebElement imgLogo;
	private @FindBy (how = How.XPATH, using = "//input[@type='button' and @value='Marketplace']") WebElement btnMarketPlace;
	
	// Constructor with the driver assigning to the parent
	public LandingPage(WebDriver driver)
	{
		super(driver);
	}
	
	// Method to click the link Welcome Admin
	public void clickWelcomeLogin()
	{
		
		welcomeAdminLink.click();
		waitForBrowserStability();
		
	}
	
	// Method to click the logout button under Welcome Admin link
	public void clickLogout()
	{
		logoutlink.click();
	}

	// Method to click the Market Place link in the landing page
	public void clickMarketPlace()
	{
		btnMarketPlace.click();
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		waitForBrowserStability();
	}
	
	// Method is to verify the whether the logo is present in the Landing page. This is to confirm the page is loaded successfully
	public boolean verifyLogo()
	{
		try
		{
			System.err.println("The value inside verifyLogo " + imgLogo.isDisplayed());	
		}catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Inside catch exception of verifyLogo method");
		}
		
		return imgLogo.isDisplayed();		
	}
}
