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

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.automate.drivers.SetupDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author aggin
 *
 */
public class BasePO {
	
	private static final int TIMEOUT = 5;
	
	public BasePO() {
		if(SetupDriver.getInstance().getSessionBrowser().contains("androidchrome") || SetupDriver.getInstance().getSessionBrowser().contains("androidapp") || SetupDriver.getInstance().getSessionBrowser().contains("iosapp")) {
			PageFactory.initElements(new AppiumFieldDecorator(SetupDriver.getInstance().getDriver(true)), this);
		}
		else {
			PageFactory.initElements(new AjaxElementLocatorFactory(SetupDriver.getInstance().getCurrentDriver(), TIMEOUT), this);
		}
	}

}
