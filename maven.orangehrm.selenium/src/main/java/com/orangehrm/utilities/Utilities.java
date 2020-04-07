package com.orangehrm.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Utilities {
	
	WebDriver driver;
	
	
	public Utilities(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	// Method to get the screenshot and copy the file in the specified path
	public String getScreenshot(String testName) 
	{
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot/" + testName + " - " + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			
			FileUtils.copyFile(src, destination);
						
		} catch(Exception e)
		{
			System.err.println("Capture Failed " + e.getMessage());
		}
		
		return path;
	} // End of getScreenShot
	

}
