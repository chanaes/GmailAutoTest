package com.ebase.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebase.qa.core.BasePage;

public class SignInPage extends BasePage {
	
	private By emailTextBox = By.id("Email");
	private By passTextBox = By.id("Passwd");
	private By nextBtn = By.id("next");
	private By signInBtn = By.id("signIn");
	private By createAccountLink = By.xpath(".//*[@id='link-signup']/a");
	private By errorMessageElement = By.cssSelector("span[class=error-msg][role=alert]");
	
	private static final String TITLE = "Sign In Page!";
	
	public static final String userErrorMessage = "Sorry, Google doesn't recognize that email.";
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String userName) {
		WebElement element = driver.findElement(emailTextBox);
		element.clear();
		element.sendKeys(userName);
	}
	
	public void enterPassword(String passWord) {
		WebElement element = driver.findElement(passTextBox);
		element.clear();
		element.sendKeys(passWord);
	}
	
	public CreateAccountPage clickCreateAccount() {
		driver.findElement(createAccountLink).click();
		return new CreateAccountPage(driver);
	}
	
	public void clickNext() {
		driver.findElement(nextBtn).click();
	}

	public UserInboxPage clickSignIn() {
		driver.findElement(signInBtn).click();
		return new UserInboxPage(driver);
	}
	
	public String getUserErrorMessage() {
		WebElement errMsg = driver.findElement(errorMessageElement);
		//AJAX
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(
				ExpectedConditions.textToBePresentInElementLocated(
						errorMessageElement, userErrorMessage));
	
		return errMsg.getText();
	}
	
	@Override
	public boolean verifyPageTitle() {
		return TITLE.equals(getPageTitle());
	}
	

}
