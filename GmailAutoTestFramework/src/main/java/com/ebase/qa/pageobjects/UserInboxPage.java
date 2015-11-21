package com.ebase.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebase.qa.core.BasePage;

public class UserInboxPage extends BasePage {

	private By composeBtn = By.xpath("//div[@id=':jb' and @class='aic']/div/div");
	private static final String TITLE = "Inbox";
	private static final String COMPOSE = "COMPOSE";

	public UserInboxPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean verifyPageTitle() {
		WebElement element = driver.findElement(composeBtn);
		System.out.println(element.getText());
		// AJAX
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(composeBtn));
		
		System.out.println(element.getText());
		
		return COMPOSE.equals(element.getText());
	}
}
