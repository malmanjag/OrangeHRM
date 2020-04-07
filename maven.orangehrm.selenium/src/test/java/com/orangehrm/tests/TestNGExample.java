package com.orangehrm.tests;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class TestNGExample {
	
	WebDriver driver;
	
	ExtentReports extent;
	ExtentTest logger;

	
	@BeforeMethod
	public void setUp() {
	
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/TestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest("pageLoadTest");
		System.out.println("Trying Github");
	}
	
	
	@Test
	public void pageLoadTest() {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/mjegathe/Documents/07. Installables/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
		System.out.println("The title of the page is " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Malar"));
		
	}
	
	@Test
	public void pageLoadTest1() {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/mjegathe/Documents/07. Installables/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
		
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
	
		if(result.getStatus() == ITestResult.FAILURE) {
			

		
		}
		
		extent.flush();
		driver.quit();
	}
}
