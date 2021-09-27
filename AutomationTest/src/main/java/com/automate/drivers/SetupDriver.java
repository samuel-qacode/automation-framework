/* 
MIT License

Copyright (c) 2020 agginsamuel

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. 
*/


/**
 * 
 */
package com.automate.drivers;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author aggin
 * Singleton class for creating webdriver
 */
public class SetupDriver {
	private static SetupDriver instance = null;
	
	private ThreadLocal<WebDriver> webDriver= new ThreadLocal<WebDriver>();
	private ThreadLocal<AppiumDriver<MobileElement>> mobileDriver= new ThreadLocal<AppiumDriver<MobileElement>>();
	private ThreadLocal<String> sessionId= new ThreadLocal<String>();
	private ThreadLocal<String> sessionBrowser= new ThreadLocal<String>();
	private ThreadLocal<String> sessionPlatform= new ThreadLocal<String>();
	private ThreadLocal<String> sessionVersion= new ThreadLocal<String>();
	
	private static final String propertyFile= new File("/Users/apple/aggin/Interview2020/SeleniumPractise/src/test/resources/selenium.properties").getAbsolutePath();
	private Properties driverProps= new Properties();
	
	/**
	 * Creating instance of a class through which we can access the methods
	 * @return SetupDriver object
	 */
	public static SetupDriver getInstance() {
		if(instance==null) {
			instance= new SetupDriver();
		}
		return instance;  	
	}
	
	/**
	 * Setting up driver based on browser and operating system with optional parameter
	 * @param browser
	 * @param environment
	 * @param platform
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public final void setDriver(String browser, String environment, String platform) throws Exception{
		DesiredCapabilities caps=null;
		driverProps.load(new FileInputStream(propertyFile));
		
		switch(browser){
		case "firefox":
			caps= DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", driverProps.getProperty("firefox.driver.in.mac"));
			FirefoxOptions ffoption= new FirefoxOptions();
			FirefoxProfile ffprofile = new FirefoxProfile();
			ffprofile.setPreference("browser.autofocus", true);
			caps.setCapability(FirefoxDriver.PROFILE,ffprofile);
			caps.setCapability("marionette",true);
			webDriver.set(new FirefoxDriver(ffoption.merge(caps)));
			break;
		case "chrome":
			String driverFilePath =CheckOS.driverLocation(browser);
			caps= DesiredCapabilities.chrome();
			ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("/Users/apple/git/automation-framework/AutomationTest/src/main/resources/drivers/mac/chromedriver"))
                    .usingAnyFreePort()
                    .build();
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String,Object> chromePrefs= new HashMap<String,Object>();
			chromePrefs.put("credentials_enable_service", false);
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			chromeOptions.addArguments("--disable-plugins","--disable-extensions","--disable-popup-blocking");
			chromeOptions.setCapability("applicationCacheEnabled", false);;
			webDriver.set(new ChromeDriver(service,chromeOptions));
			break;
		case "safari":
			caps= DesiredCapabilities.safari();
			webDriver.set(new SafariDriver(caps));
			break;
		case "androidchrome":
			caps= DesiredCapabilities.android();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "galaxy S6");
			caps.setCapability(MobileCapabilityType.UDID, "051605b823881b01");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			mobileDriver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps));
			sessionId.set(((AndroidDriver<MobileElement>)mobileDriver.get()).getSessionId().toString());
			sessionBrowser.set(browser);
			sessionVersion.set(caps.getCapability("deviceName").toString());
			break;
		case "androidapp":
			caps= DesiredCapabilities.android();
			caps.setCapability("platformName", "Android");
			caps.setCapability("browserName", "");
			caps.setCapability("platformVersion", "7.0");
			caps.setCapability("deviceName", "Samsung Galaxy S6 Device");
			//caps.setCapability(MobileCapabilityType.UDID, "051605b823881b01");
			//caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
			caps.setCapability("appium-version", "1.20.0");
			//caps.setCapability("appPackage", "com.android.chrome"); 
			caps.setCapability("appPackage", "com.duckduckgo.mobile.android.debug");
			//caps.setCapability("appActivity","com.google.android.apps.chrome.Main");
			caps.setCapability("appActivity","com.duckduckgo.app.browser.BrowserActivity");
			mobileDriver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps));
			sessionId.set(((AndroidDriver<MobileElement>)mobileDriver.get()).getSessionId().toString());
			sessionBrowser.set(browser);
			sessionVersion.set(caps.getCapability("deviceName").toString());
			break;
		case "iosapp":
			caps= DesiredCapabilities.iphone();
			caps.setCapability("platformName", "iOS");
			caps.setCapability("browserName", "");
			caps.setCapability("platformVersion", "14.1");
			caps.setCapability("deviceName", "iPhone Simulator");
			caps.setCapability("appium-version", "1.20.0"); 
			//caps.setCapability("automationName", "XCUITest");
			caps.setCapability("udid", "B3801FC9-67B1-40BF-809A-D05A3530D7FF");
			caps.setCapability("bundleId", "com.duckduckgo.mobile.ios.local");
			mobileDriver.set(new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps));
			sessionId.set(((IOSDriver<MobileElement>)mobileDriver.get()).getSessionId().toString());
			sessionBrowser.set(browser);
			sessionVersion.set(caps.getCapability("deviceName").toString());
		}
		if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("safari")) {
			sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
			sessionBrowser.set(caps.getBrowserName());
			sessionVersion.set(caps.getVersion());
			sessionPlatform.set(platform);
		}

	}
	
	
	public WebDriver getDriver() {
		return webDriver.get();
	}
	
	public AppiumDriver<MobileElement> getDriver(boolean mobile) {
		return mobileDriver.get();
	}
	
	public String getSessionId() {
		return sessionId.get();
	}
	
	public String getSessionBrowser() {
		return sessionBrowser.get();
	}
	
	public String getSessionVersion() {
		return sessionVersion.get();
	}
	
	public String getSessionPlatform() {
		return sessionPlatform.get();
	}
	

	public WebDriver getCurrentDriver() {
		if(getInstance().getSessionBrowser().contains("androidchrome") || getInstance().getSessionBrowser().contains("androidapp") || getInstance().getSessionBrowser().contains("iosapp")) {
			System.out.println(getInstance().getSessionBrowser().toString());
			return getInstance().getDriver(true);	
		}
		else {
			return getInstance().getDriver();
		}
	}
	
	public void driverWait(long seconds) {
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void driverClose() {
		try {
			getCurrentDriver().close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
