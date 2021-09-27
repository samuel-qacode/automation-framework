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
package com.automate.commonutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

/**
 * @author aggin
 *
 */
public class ReadCSV {
	
	private static final String propertyFileLocation= new File("/Users/apple/git/automation-framework/AutomationTest/src/main/resources/files/selenium.properties").getAbsolutePath();
	CSVReader reader;
	CSVParser parser;
	Properties propertyFile;
	HashMap<String,Object> testDataMap = new HashMap<String,Object>();
	ReadCSVMutator csvMutator;
	
	public HashMap<String, Object> getTestData() throws FileNotFoundException, IOException, CsvValidationException{
		propertyFile =new Properties();
		propertyFile.load(new FileInputStream(propertyFileLocation));
		String csvFile = propertyFile.getProperty("testdata.csv");
		System.out.println(csvFile);
		parser = new CSVParserBuilder()
			    .withSeparator(',')
			    .withIgnoreQuotations(true)
			    .build();
			 
		reader = new CSVReaderBuilder(new FileReader(csvFile))
			    .withSkipLines(0)
			    .withCSVParser(parser)
			    .build();
			
			String[] cellValue = null;
			String testCaseName= "";

			while ((cellValue = reader.readNext()) != null) {
				csvMutator = new ReadCSVMutator();
				testCaseName = cellValue[0];
				csvMutator.setTestUrl(cellValue[1]);
			}
			testDataMap.put(testCaseName,csvMutator);
			return testDataMap;
		
	}

}
