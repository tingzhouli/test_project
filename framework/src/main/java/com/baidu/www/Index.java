package com.baidu.www;

import org.openqa.selenium.WebDriver;

import com.automan.framework.page.BasePage;
import com.automan.framework.util.Locator;
import com.automan.framework.util.YamlGather;

public class Index extends BasePage{
	
	public WebDriver driver = null;
	
	public YamlGather yg = new YamlGather();
	
//	public String pageObjectFile = "/Users/yingchen/Documents/workspace/framework/src/main/java/com/automan/framework/index.yml";
//	public String pageObjectFile = "D:/Apps/framework/src/main/java/com/automan/framework/index.yml";
	public String pageObjectFile = "src/conf/page/www.baidu.com/index.html.yml";
	
	public Index(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	
	public Locator searchKeyInputBox = new Locator("searchKeyInputBox", 
			getPageObject("searchKeyInputBox", pageObjectFile)); 
	public Locator submitInputBox = new Locator("submitInputBox", 
			getPageObject("submitInputBox", pageObjectFile));
	

	
	
	
}
