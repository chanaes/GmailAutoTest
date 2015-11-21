package com.ebase.qa.gmail.tests;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ebase.qa.core.tests.BaseTest;
import com.ebase.qa.pageobjects.CreateAccountPage;
import com.ebase.qa.pageobjects.SignInPage;
import com.ebase.qa.pageobjects.UserInboxPage;

public class SignInTest extends BaseTest {

	private SignInPage signInPage;
	private CreateAccountPage createAccountPage;
	
	private static final String USER_TEST_DATA_FILE_XSL = "F:\\temp\\gmailUserTestData.xlsx";

	@BeforeClass
	public void setUp() {
		super.init();
		signInPage = new SignInPage(driver);
	}

	@BeforeMethod
	public void initTest() {
		driver.navigate().to(APP_URL);
	}

	@Test(enabled=false)
	public void verifyUser() {
		signInPage.enterUserName("kumar@gmail.com");
		signInPage.clickNext();

		Assert.assertTrue(true);
	}

	@Test(enabled=false)
	public void verifyCreateAccountLink() {
		createAccountPage = signInPage.clickCreateAccount();
		Assert.assertTrue(createAccountPage.verifyPageTitle());
	}

	@Test(enabled=false)
	public void userNameNumbers() {
		signInPage.enterUserName("12341234");
		signInPage.clickNext();

		Assert.assertEquals(SignInPage.userErrorMessage, signInPage.getUserErrorMessage());
	}

	@Test(dataProvider = "userTestData")
	public void userLogin(String userName, String passWord) {
		System.out.println("UserName/Password" + userName + "/" + passWord);
		signInPage.enterUserName(userName);
		signInPage.clickNext();
		signInPage.enterPassword(passWord);

		UserInboxPage userInboxPage = signInPage.clickSignIn();
		System.out.println(userInboxPage.getPageTitle());
		Assert.assertTrue(userInboxPage.verifyPageTitle());
	}

	@DataProvider(name = "userTestData")
	public Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream(USER_TEST_DATA_FILE_XSL);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh1 = wb.getSheetAt(0);
		
		int numrows = sh1.getLastRowNum() - sh1.getFirstRowNum();
		int numcols = sh1.getRow(1).getLastCellNum();
		
		System.out.println(numrows + ";" + numcols);
		
		Object[][] gmailAcctData = new Object[numrows][numcols];
		for (int i = 0; i < numrows; i++) {
			for (int j = 0; j < numcols; j++) {
				gmailAcctData[i][j] = sh1.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return gmailAcctData;
	}

}
