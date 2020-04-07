package com.orangehrm.pages;

import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PageObject {

	static final Logger logger = LogManager.getLogger();

	protected WebDriver driver;


	public PageObject(WebDriver driver)
	{		
		this.driver = driver;		
	}

	public void setValue(WebElement element, String valueToSet)
	{
		element.click();
		element.clear();
		element.sendKeys(valueToSet);
		//logger(valueToSet+" shoul set in "+element.toString());
	}


	// Method to get the number of seconds to wait to proceed further. Input received in seconds
	public void waitForSeconds(int seconds)
	{
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

	}

	// Method is to wait for the browser to be ready for use
	public void waitForBrowserStability()
	{

		boolean browserReady = false;
		int i = 0;

		try
		{		
			// This loop helps to call JS script to wait until the browser is stable for further use
			while(i < 5)
			{
				Thread.sleep(1000);
				browserReady = ((JavascriptExecutor)driver).executeScript("return document.readyState")
						.toString().equals("complete");
				if(browserReady)
				{
					System.out.println("The value if i inside browserfor stability is " + i);
					break;
				}
				else
					i++;	
			}
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Issue occurred while waiting for the browser stability");
		}

	}

}
