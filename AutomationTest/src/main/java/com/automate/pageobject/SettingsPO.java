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
package com.automate.pageobject;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.automate.drivers.SetupDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * @author aggin
 *
 */
public class SettingsPO extends BasePO {
	
	//private static final int TIMEOUT = 5;
	
	//@FindBy(id= "com.duckduckgo.mobile.android.debug:id/browserMenu")
	@FindBy(xpath ="//a[@class='header__button--menu  js-side-menu-open']")
	@AndroidFindBy(id = "com.duckduckgo.mobile.android.debug:id/browserMenu")
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name == 'SETTINGS'")
	private WebElement openSettings;
	
	public SettingsPO() {
		//PageFactory.initElements(new AppiumFieldDecorator(SetupDriver.getInstance().getDriver(true)), this);
		//PageFactory.initElements(new AjaxElementLocatorFactory(SetupDriver.getInstance().getCurrentDriver(), TIMEOUT), this);
		super();
		
	}
	
	public SettingsPO clickSetting () {
		System.out.println(SetupDriver.getInstance().getCurrentDriver().getPageSource());
		AppiumDriver<MobileElement> driver = SetupDriver.getInstance().getDriver(true);
		Set<String> contextName = driver.getContextHandles();
		System.out.println("----------------");
		System.out.println("1"+contextName.toArray()[0].toString());
		System.out.println("2"+contextName.toArray()[1].toString());
		driver.context(contextName.toArray()[1].toString());
		System.out.println("**********"+openSettings.getText());
		openSettings.click();;
		return this;
	}

}
