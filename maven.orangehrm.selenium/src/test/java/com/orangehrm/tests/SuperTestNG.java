package com.orangehrm.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SuperTestNG {

	WebDriver driver;

	ExtentHtmlReporter reporter;
	ExtentReports extent;
	ExtentTest logger;

	String screenShot = "";
	String strBrowser = "";
	String strBaseUrl="";
	//1. read from a property file and store the value of browser to strBrowser
	
	InputStream input;
		

	// Method to launch the application with the provided browser by loading data from properties file
	@BeforeTest
	public void setup()throws InterruptedException, MalformedURLException, IOException {		
		try {
			
			// Stream the data from the config properties file and store in the object input
			input = new FileInputStream("./config.properties");
			Properties prop = new Properties();
			
			// Load the data into prop object
			prop.load(input);
			
			//Assign the values into the variables taken from config properties file
			strBrowser = prop.getProperty("BROWSER");
			strBaseUrl = prop.getProperty("BASE_URL");
			
			//Choose the browser and launch by getting the values from config properties file
			if(strBrowser.equalsIgnoreCase("chrome")) {
				
				//Sets the property of the chrome driver
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				
				driver = new ChromeDriver();
				
				// Yet to provide the details for both IE and firefox below
			} else if(strBrowser.equalsIgnoreCase("ie")) {
				System.setProperty("", "");
				driver = new InternetExplorerDriver();
			} else if(strBrowser.equalsIgnoreCase("firefox")) {
				System.setProperty("", "");
				driver = new FirefoxDriver();
			}
			
			driver.get(strBaseUrl);
			driver.manage().window().maximize();
			
		} catch(Exception e) {
			System.out.println("Provide a valid data for driver");
			e.printStackTrace();
		}

	}
	
	// Method to initiate the extent report to collect details of the run
	@BeforeClass
	public void initiate()
	{
		reporter = new ExtentHtmlReporter("./Reports/TestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	// Method to run for every test case to capture the screenshot and log it in the report appropriately
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{

		//screenShot = utilityObject.getScreenshot(testName);

		if(result.getStatus() == ITestResult.FAILURE) 
			logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(screenShot).build());

		/*
		 * else if(result.getStatus() == ITestResult.SUCCESS)
		 * logger.pass("Test Executed Successfully",
		 * MediaEntityBuilder.createScreenCaptureFromPath(screenShot).build());
		
		 */
		extent.flush();
	}
	


	// Method to flush the report at the end of the test execution of all test cases
	@AfterClass
	public void windUp()
	{
		
		driver.close();
	}
	
	@AfterTest
	public void closeAll()
	{
		driver.quit();
		
	}

}
