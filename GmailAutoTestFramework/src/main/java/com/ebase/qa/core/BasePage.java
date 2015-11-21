package com.ebase.qa.core;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
	
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void navigateTo(String url) {
		driver.navigate().to(url);
	}
	
	public abstract boolean verifyPageTitle();
}
