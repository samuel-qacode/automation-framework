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
package com.automate.test.search;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automate.pageobject.SearchPO;
import com.automate.pageobject.SettingsPO;
import com.automate.report.ExtentTestNGITestListener;
import com.automate.test.base.TestBaseSetup;

/**
 * @author aggin
 *
 */
public class TestSearchFunctionality extends TestBaseSetup{
	
	
	SearchPO search;
	SettingsPO settings;
	
	@BeforeClass
	public void beforeClass() {
		logger.info("this is first test message-2");
		System.out.println("variable ");
		search = new SearchPO();
		settings = new SettingsPO();
	}
	
	@Test
	public void test1() throws InterruptedException {
		System.out.println("inside test");
		logger.info("this is first test -1");
		Thread.sleep(6000);
		System.out.println("before wait");

		settings.clickSetting();
		System.out.println("after wait");
//		ExtentTestNGITestListener.childTest.get().info("checking the test steps");
//		search.enterSearchKeyword("India").andSearch();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void test2() {
		logger.info("this is first test -2");
	}
	
	@Test
	public void test3() {
		logger.info("this is first test -3");
	}

}
