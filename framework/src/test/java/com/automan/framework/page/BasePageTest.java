package com.automan.framework.page;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePageTest {
	protected WebDriver driver = null;
	protected String expectResult = "";
	protected String actualResult = "";
	
	@Before
	public void setUp() throws Exception {
		this.driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit(); 
	}

	@Ignore
	public void testBasePage() {	
		fail("Not yet implemented");
	}

	@Ignore
	public void testGet() {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("http://www.baidu.com");
			assertEquals(bp.getPageTitle(), "百度一下，你就知道");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	public void testIsPageLoadCompleted() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testIsJQueryLoadCompleted() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testRefreshBrowser() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testForwardBrowser() {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("http://www.51job.com");
			bp.get("http://www.baidu.com");
			bp.backBrowser();
			bp.forwardBrowser();
			assertEquals(bp.getPageTitle(), "百度一下，你就知道");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	public void testBackBrowser() {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("http://www.baidu.com");
			if (bp.isPageLoadCompleted())
				bp.get("http://www.51job.com");
			if (bp.isPageLoadCompleted())
				bp.backBrowser();
			assertEquals(bp.getPageTitle(), "百度一下，你就知道");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetPageTitle() {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("http://www.baidu.com");
			assertEquals(bp.getPageTitle(), "百度一下，你就知道");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetPageUrl() {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("http://www.baidu.com");
			if (bp.isPageLoadCompleted()){
				assertEquals(bp.getPageUrl(), "http://www.baidu.com/");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetPageCode() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testResizeTo() {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("http://www.baidu.com");
			bp.resizeTo(800, 600);;
			Dimension d = driver.manage().window().getSize();
			assertEquals(d.getWidth(), 800);
			assertEquals(d.getHeight(), 600);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	public void testMaxBrowser() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testMoveBrowserTo() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testAddSingleCookie() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetSingleCookie() throws Exception {
		try{
			BasePage bp = new BasePage(driver);
			bp.get("https://login.51job.com/login.php?lang=c");
			
//			case 1
			if (bp.isPageLoadCompleted()){
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d = sdf.parse("2017-04-30 00:00:00");
				Cookie c = new Cookie("gguid", "1234567890", ".51job.com", "/", d);
				bp.addSingleCookie(c);
				actualResult = bp.getSingleCookie("gguid").toString();
				expectResult = "gguid=1234567890; expires=星期日, 30 四月 2017 12:00:00 CST; path=/; domain=.51job.com";
				assertEquals(actualResult, expectResult);	
			}
			
//			case 2
			if (bp.isPageLoadCompleted()){
				assertNull(bp.getSingleCookie("aabbcc"));	
			}
			
//			case 3
			if (bp.isPageLoadCompleted()){
				assertNull(bp.getSingleCookie(""));	
			}
			
//			case 4
			if (bp.isPageLoadCompleted()){
				assertNull(bp.getSingleCookie(null));	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	@Test(expected = Exception.class)
//	public void testGetSingleCookieException() throws Exception {
//		BasePage bp = new BasePage(driver);
//		bp.get("https://www.baidu.com");
//		if (bp.isPageLoadCompleted()){
//			bp.getSingleCookie(null);
//			fail("testGetSingleCookieException has non exception.");	
//		}
//	}

	@Ignore
	public void testDeleteSingleCookie() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetAllCookies() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDeleteAllCookies() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetWindowHandles() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSwitchWindowWithUrl() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSwitchWindowWithTitle() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSwitchWindowWithTitlePattern() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSwitchJSAlert() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testCloseAlertAndGetItsText() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSetWaitTime() {
		fail("Not yet implemented");
	}

}
