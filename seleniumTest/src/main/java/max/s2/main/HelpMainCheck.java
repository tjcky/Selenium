/*
 * @(#)HelpMainCheck.java $version 2016. 02. 03
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package max.s2.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Kwon Young
 */
public class HelpMainCheck {
	public static void main(String[] args) {
		HelpMainCheck helpMainCheck = new HelpMainCheck();
		helpMainCheck.doStart();
	}

	private void doStart() {
		// The Firefox driver supports javascript
		WebDriver driver = new FirefoxDriver();

		// Go to the Google Suggest home page
		driver.get("https://help.naver.com/support/home.nhn");


	}
}
