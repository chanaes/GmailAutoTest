package com.ebase.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

	private static final String DRIVER_PROPS = "driver.properties";
	private static final String DRIVER_PROPS_LOC = "F:\\temp\\GmailAutoTestFramework\\src\\test\\resources\\";
	private static final String BROWSER_KEY = "browser.key";

	private static Properties properties = null;

	private static DesiredCapabilities ieCapabilities = null;
	private static ChromeOptions chromeOptions = null;

	private static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	private static final String CHROME_DRIVER_PATH = "F:\\Selenium\\downloads\\chromedriver_win32\\chromedriver.exe";
	private static final String CHROME_DRIVER_REL_PATH = "chromedriver.exe";

	private static final String IE_DRIVER_KEY = "webdriver.ie.driver";
	private static final String IE_DRIVER_PATH = "F:\\Selenium\\downloads\\IEDriverServer_x64_2.47.0\\IEDriverServer.exe";
	private static final String IE_DRIVER_REL_PATH = "IEDriverServer.exe";

	public static final String WEBELEMENTS_TEST_URL = "file:///C:/Users/Sharat/git/upskill/SeleniumTraining/HTMLQAPage.html";
	public static final String AUTOTRADER_TEST_URL = "http://www.autotrader.com";
	public static final String GOOGLE_URL = "http://www.google.com";

	/*
	 * 
	 */
	static {

		if (properties == null) {
			properties = new Properties();
			InputStream in = null;
			try {
				
				try {
					in = DriverFactory.class.getResourceAsStream(DRIVER_PROPS);
				} catch (Exception e) {
					in = new FileInputStream(DRIVER_PROPS_LOC + DRIVER_PROPS);
				}

				properties.load(in);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println(properties);
		//
		if (ieCapabilities == null)
			initIECapabilities();
		//
		if (chromeOptions == null)
			initChromeOptions();

	}

	/*
	 * Get the Driver as specified in the properties with no initialization
	 * options
	 */
	public static WebDriver getDriver() {
		String browserName = properties.getProperty(BROWSER_KEY);
		System.out.println(browserName);
		return getDriver(browserName, false);
	}

	/*
	 * 
	 */
	public static WebDriver getDriver(String browser) {
		return getDriver(browser, false);
	}

	/*
	 * 
	 */
	public static WebDriver getDriver(String browser, boolean initOptions) {

		WebDriver driver = null;

		if (browser == null || browser.length() == 0)
			throw new IllegalArgumentException("Provide valid Browser Name..." + browser);

		if (Browser.CHROME.getValue().equals(browser)) {
			System.setProperty(CHROME_DRIVER_KEY, CHROME_DRIVER_REL_PATH);
			driver = initOptions ? new ChromeDriver(chromeOptions) : new ChromeDriver();

		} else if (Browser.IE.getValue().equals(browser)) {
			System.setProperty(IE_DRIVER_KEY, IE_DRIVER_REL_PATH);
			driver = initOptions ? new InternetExplorerDriver(ieCapabilities) : new InternetExplorerDriver();

		} else if (Browser.FIREFOX.getValue().equals(browser)) {
			driver = new FirefoxDriver();

		} else {
			throw new IllegalArgumentException("No Driver for this Browser..." + browser);
		}

		return driver;

	}

	//
	private static void initIECapabilities() {
		ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
		ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
	}

	//
	private static void initChromeOptions() {
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized"); // To maximize the
														// browser
		chromeOptions.addArguments("--test-type"); // To get rid off 'ignore //
													// certificate errors'
													// message
		chromeOptions.addArguments("--kiosk"); // To enable full screen mode
		chromeOptions.addArguments("chrome.switches", "--disable-extensions");
	}

	/*
	 * Test the DriverFactory
	 */
	public static void main(String args[]) {

		WebDriver driver = DriverFactory.getDriver("ie", true);

		System.out.println(driver);

		driver.get("http://www.autotrader.com");

		System.out.println();

	}

}
