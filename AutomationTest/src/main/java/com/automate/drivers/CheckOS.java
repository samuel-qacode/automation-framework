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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author aggin
 *
 */
public class CheckOS {
	
	private static final String propertyFile= new File("/Users/apple/git/automation-framework/AutomationTest/src/main/resources/files/selenium.properties").getAbsolutePath();
	
	
	public static String driverLocation (String browserName) throws FileNotFoundException, IOException {
		Properties driverProps= new Properties();
		driverProps.load(new FileInputStream(propertyFile));
		String os[] = System.getProperty("os.name").split("\\s+");
		String driverLocation = os[0].toLowerCase()+"."+browserName;
		System.out.println(driverProps.getProperty(driverLocation));
		return driverLocation;	
		
	}

}
