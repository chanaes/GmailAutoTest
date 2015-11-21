package com.ebase.qa.pageobjects;

import org.openqa.selenium.WebDriver;

import com.ebase.qa.core.BasePage;

public class CreateAccountPage extends BasePage {
	
	// Define the By locators (properties)
	
	private static final String TITLE = "Create Your Google Account!";
	
	public CreateAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public boolean verifyPageTitle() {
		return TITLE.equalsIgnoreCase(getPageTitle());
	}

}
