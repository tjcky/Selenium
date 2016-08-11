/*
 * @(#)HelpMainCheck.java $version 2016. 02. 03
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package s2.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Kwon Young
 */
@RunWith(MockitoJUnitRunner.class)
public class HelpMainCheck {
	private static final String NAVER_HELP_MAIN_URL = "https://help.naver.com/support/home.nhn";
	private static final int THIRTY_SECONDS = 30;
	private static WebDriver driver;
	private static JavascriptExecutor js;

	@Before
	public void before() {
		//		System.setProperty("webdriver.edge.driver", "F:/dev_tool/selenium/IEDriverServer.exe");
		//		driver = new EdgeDriver();
		driver = new FirefoxDriver(); // firefoxDriver 객체 생성.
		driver.manage().window().maximize(); // firefox의 window사이즈를 최대창으로 지정.
		driver.manage().timeouts().implicitlyWait(THIRTY_SECONDS, TimeUnit.SECONDS); // 엘리먼트를 탐색하는 wait-time설정.
		js = (JavascriptExecutor) driver;
		driver.get(NAVER_HELP_MAIN_URL); // 네이버 고객센터 접근
	}

	@After
	public void after() {
		driver.quit(); // TEST가 완료 후 firfox창을 닫음.
	}

	@Test
	public void test정상적인_메인화면() {
		assertThat(driver.getTitle(), is("네이버 고객센터")); // 현재 페이지의 title값을 가져와 비교.
	}

	@Test
	public void test신고페이지로이동() {
		driver.findElement(By.xpath(HelpXPath.REPORT_CENTER_IMAGE)).click(); // 유해게시물 신고이미지클릭

		assertThat(driver.findElement(By.xpath(HelpXPath.REPORT_SERVICE_TITLE)).getText(), is("신고센터"));

		assertThat(driver.getTitle(), is("네이버 고객센터"));
	}

	@Test
	public void test네이버페이결제도용_신고이동() {
		driver.findElement(By.xpath(HelpXPath.NAVER_PAY_CENTER_IMAGE)).click(); // 유해게시물 신고이미지클릭

		assertThat(driver.getTitle(), is("네이버 : 로그인"));
	}

	@Test
	public void test저작권침해게시물_신고이동() {
		driver.findElement(By.xpath(HelpXPath.CPC_CENTER_IMAGE)).click(); // 유해게시물 신고이미지클릭

		assertThat(driver.getTitle(), is("저작권 보호 센터 : 네이버 고객센터"));
	}

	@Test
	public void test명예훼손게시물_신고이동() {
		driver.findElement(By.xpath(HelpXPath.INOTI_SERVICE_IMAGE)).click(); // 유해게시물 신고이미지클릭

		assertThat(driver.getTitle(), is("게시중단요청서비스"));
	}

	@Test
	public void test고객센터메인_이미지테그가_정상적으로_18개인가() {
		assertThat(driver.findElements(By.xpath(HelpXPath.NAVER_HELP_MAIN_SERVICE_IMAGES)).size(), is(18));
	}

	@Test
	public void testJQUERY사용방식(){
		js.executeScript("jQuery('#searchText').click()");
		js.executeScript("jQuery('#searchText').val('검색')");
		js.executeScript("jQuery('#ischButton').click()");

		assertThat(driver.findElement(By.xpath("//*[@id='help_area']/h2")).getText(), is("도움말 검색결과"));
	}
}
