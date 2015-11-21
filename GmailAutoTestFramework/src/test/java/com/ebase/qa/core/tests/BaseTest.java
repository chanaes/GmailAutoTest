package com.ebase.qa.core.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import com.ebase.qa.utils.DriverFactory;

public class BaseTest {
	
	protected WebDriver driver;
	protected static final String APP_URL = "http://www.gmail.com";
	
	public void init() {
		driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(APP_URL);
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
