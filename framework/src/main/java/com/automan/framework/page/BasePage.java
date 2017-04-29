package com.automan.framework.page;

import java.io.FileReader;
import java.io.IOException;
//import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automan.framework.util.Locator;
import com.automan.framework.util.Locator.ByType;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.automan.framework.util.YamlGather;

//import com.job.automan.framework.util.Locator;

public class BasePage {
	public WebDriver driver = null;
	
    /**
     * 构造方法
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public BasePage(WebDriver driver){
		this.driver = driver;
	}
	
    /**
     * 获取driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public WebDriver getDriver(){
		return driver;
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public WebElement getElement(Locator locator){
		return getElement(this.getDriver(), locator);
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public WebElement getElement(WebDriver driver, Locator locator){
//		locator = getLocator(locator.getElement());
		WebElement el = null;
		switch(locator.getByType()){
			case xpath:
				el = driver.findElement(By.xpath(locator.getWhat()));
				break;
			case id:
				el = driver.findElement(By.id(locator.getWhat()));
				break;
			case name:
				el = driver.findElement(By.name(locator.getWhat()));
				break;
			case cssSelector:
				el = driver.findElement(By.cssSelector(locator.getWhat()));
				break;
			case className:
				el = driver.findElement(By.className(locator.getWhat()));
				break;
			case tagName:
				el = driver.findElement(By.tagName(locator.getWhat()));
				break;
			case linkText:
				el = driver.findElement(By.linkText(locator.getWhat()));
				break;
			case partialLinkText:
				el = driver.findElement(By.partialLinkText(locator.getWhat()));
				break;
			default:
				el = driver.findElement(By.xpath(locator.getWhat()));
		}
		return el;
	}
	
	public Map getPageObject(String name, String pageObjectFile){
		YamlReader reader;
		try {
			reader = new YamlReader(new FileReader(pageObjectFile));
			Map<String, Map> map = (Map) reader.read();
			return map.get(name);
//			List<Map> list = (List<Map>) reader.read();
//			for(Map detailMap:list){
//				if (detailMap.get("name").equals(object))
//					return detailMap;
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
//	public Locator getLocator(String locatorName){
//		
//		Locator locator = new Locator("//input[@id='kw']", 1, ByType.xpath);
//        return locator;
//	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public void type(Locator locator, String values){
		WebElement el = findElement(driver, locator);
		el.sendKeys(values);
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public void clickAndHold(Locator locator){
		WebElement el = findElement(driver, locator);
		Actions actions = new Actions(driver);
		actions.clickAndHold();
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public WebElement findElement(WebDriver driver, final Locator locator){
		WebElement el = (new WebDriverWait(driver, locator.getWaitSecs())).
				until(new ExpectedCondition<WebElement>(){
					
			@Override
			public WebElement apply(WebDriver driver){
				try{
					return getElement(driver, locator);
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
			}
		});
		return el;
	}
	
    /**
     * 存入driver
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public void click(Locator locator){
		WebElement el = findElement(driver, locator);
		el.click();
	}
	
    /**
     * 访问指定url的页面
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public void get(String url) throws Exception{
		driver.get(url);
	}
	
    /**
     * 页面是否加载完毕
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public boolean isPageLoadCompleted() throws Exception{
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			while(true){
				if ((boolean) (js.executeScript("return document.readyState == 'complete';"))){
					break;
				}
//				wait for 100 mills
				Thread.sleep(500);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
    /**
     * 页面jquery请求是否加载完毕
     * 
     * @author litingzhou
     * @param 
     * @return boolean
     * @throws InterruptedException
     */
	public boolean isJQueryLoadCompleted() throws Exception{
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			while(true){
				if ((boolean) (js.executeScript(("return jQuery.active == 0;")))){
					break;
				}
//				wait for 100 mills
				Thread.sleep(500);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
    /**
     * 浏览器刷新
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void refreshBrowser() throws Exception{
		driver.navigate().refresh();
	}
	
    /**
     * 浏览器前进
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void forwardBrowser() throws Exception{
		driver.navigate().forward();;
	}
	
    /**
     * 浏览器后退
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void backBrowser() throws Exception{
		driver.navigate().back();
	}
	
    /**
     * 获取当前页标题
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public String getPageTitle() throws Exception{
		return driver.getTitle();
	}
	
    /**
     * 获取当前页url
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public String getPageUrl() throws Exception{
		return driver.getCurrentUrl();
	}
	
    /**
     * 获取当前页源码
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public String getPageCode() throws Exception{
		return driver.getPageSource();
	}
	
    /**
     * 修改浏览器大小
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void resizeTo(int x, int y) throws Exception{
		Dimension d = new Dimension(x, y);
		driver.manage().window().setSize(d);
	}
	
    /**
     * 浏览器最大化
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void maxBrowser() throws Exception{
		driver.manage().window().maximize();
	}
	
    /**
     * 浏览器移动至特定位置
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void moveBrowserTo(int x, int y) throws Exception{
		Point p = new Point(x, y);
		driver.manage().window().setPosition(p);
	}
	
    /**
     * 新增cookie
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void addSingleCookie(Cookie c) throws Exception{
		driver.manage().addCookie(c);
	}
	
    /**
     * @throws Exception 
     * 获取单个cookie
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public Cookie getSingleCookie(String name) throws Exception{
		Cookie cookie = driver.manage().getCookieNamed(name);
		return cookie;
	}
	
    /**
     * 删除单个cookie
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void deleteSingleCookie(String c) throws Exception{
		driver.manage().deleteCookieNamed(c);
	}
	
	/**
     * 获取所有cookie
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public Set getAllCookies() throws Exception{
		Set<Cookie> allCookies = driver.manage().getCookies();
		return allCookies;
	}
	
	/**
     * 删除所有cookie
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void deleteAllCookies() throws Exception{
		driver.manage().deleteAllCookies();
	}
	
	/**
     * 获取浏览器所有实例
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public Set getWindowHandles() throws Exception{
		Set<String> handles = driver.getWindowHandles();
		return handles;
	}
	
	/**
     * 按url切换浏览器实例
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void switchWindowWithUrl(String url) throws Exception{
		try{
			Thread.sleep(1000);
			Set<String> handles = this.getWindowHandles();
			Iterator i = handles.iterator();
			while(i.hasNext()){
//				System.out.println(i.next());
				driver.switchTo().window((String) i.next());
				if (url.equals(driver.getCurrentUrl())){
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * 按页面标题切换浏览器实例
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void switchWindowWithTitle(String title) throws Exception{
		try{
			Thread.sleep(1000);
			Set<String> handles = this.getWindowHandles();
			Iterator i = handles.iterator();
			while(i.hasNext()){
//				System.out.println(i.next());
				driver.switchTo().window((String) i.next());
				if (title.equals(driver.getTitle())){
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * 按页面标题的正则表达式切换浏览器实例
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void switchWindowWithTitlePattern(String pattern) throws Exception{
		try{
			Thread.sleep(1000);
			Set<String> handles = this.getWindowHandles();
			for (Iterator i = handles.iterator(); i.hasNext();){
//				System.out.println(i.next());
				driver.switchTo().window((String) i.next());
				if (Pattern.matches(pattern, driver.getTitle().toString())){
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * 切换至js弹窗
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void switchJSAlert(String pattern) throws Exception{
		driver.switchTo().alert();
	}
	
	/**
     * 获取js弹窗内容，并关闭弹窗
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public String closeAlertAndGetItsText(Boolean isClosed) throws Exception{
		String text = "";
		Alert alert = driver.switchTo().alert();
		text = alert.getText();
		if (isClosed){
			alert.accept();
		} else {
			alert.dismiss();
		}
		return text;
	}
	
	/**
     * 修改等待时间（全局）
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
//	public boolean isElementExisted(Locator locator){
//		this.setWaitTime(1000);
//		List<WebElement> els = findElements(driver, locator);
//		this.setWaitTime(30000);
//		if (els.size() > 0){
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	/**
     * 修改等待时间（全局）
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
	public void setWaitTime(long mills) throws Exception{
		driver.manage().timeouts().implicitlyWait(mills, TimeUnit.MILLISECONDS);	
	}
	
	/**
     * 根据option值选择select控件
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
//    public void selectByValue(Locator locator, String value){
//        WebElement el = findElement(driver, locator);
//        Select sl = new Select(el);
//        sl.selectByValue(value);
//    }
    
	/**
     * 根据option索引值选择select控件
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
//    public void selectByIndex(Locator locator, int i){
//        WebElement el = findElement(driver, locator);
//        Select sl = new Select(el);
//        sl.selectByIndex(i);
//    }
    
	/**
     * 根据option文本值选择select控件
     * 
     * @author litingzhou
     * @param 
     * @return 
     * @throws 
     */
//    public void selectByText(Locator locator, String t){
//        WebElement el = findElement(driver, locator);
//        Select sl = new Select(el);
//        sl.selectByVisibleText(t);
//    }
	
	
	
}
