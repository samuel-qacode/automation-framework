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
package com.automate.test.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automate.drivers.SetupDriver;

/**
 * @author aggin
 *
 */
public class TestBaseSetup {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@BeforeSuite
	@Parameters({"browserName"})
	protected void setup(@Optional("chrome") String browserName) {
		logger.info("You are testing on {} browser",browserName);
		System.out.println("parent");
		  try {
			  SetupDriver.getInstance().setDriver(browserName, "mac", "local");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 // SetupDriver.getInstance().getCurrentDriver().get("https://duckduckgo.com/");

	}
	
	@AfterSuite
	protected void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		SetupDriver.getInstance().getCurrentDriver().close(); 
	}

}
