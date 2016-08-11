/*
 * @(#)SeleniumTest.java $version 2016. 02. 03
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package max;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Kwon Young
 */
public class SeleniumTest {
	public static WebDriver driver;

	public SeleniumTest() {
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		driver = new InternetExplorerDriver(caps);

		File file = new File("C:\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

		try {
			doStart();
		} catch (InterruptedException e) {
		}
	}

	private void doStart() throws InterruptedException {
		try {
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(Constant.KCM_WEBCC_ALPHA); // (알파) KCM 웹 고객센터 URL로 이동합니다.

			writeAdvertiserInfomation();
		} catch (Exception e) {
			driver.quit();
		} finally {
			driver.quit();
		}

	}

	public void writeAdvertiserInfomation() {
		// input box에 광고주 정보를 입력합니다.
		driver.findElement(By.xpath(Constant.ADVERTISER_NAME)).sendKeys("권영");
		driver.findElement(By.xpath(Constant.ADVERTISER_MAIL_INPUT)).sendKeys("tjcky");
		Select mailSelection = new Select(driver.findElement(By.xpath(Constant.ADVERTISER_MAIL_SELECTION)));
		mailSelection.selectByIndex(Constant.NAVER_MAIL); // naver.com 선택
		driver.findElement(By.xpath(Constant.ADVERTISER_PHONE_NUMBER)).sendKeys("010-9877-6225");
	}

	public static void main(String[] args) {
		new SeleniumTest();
	}
}
