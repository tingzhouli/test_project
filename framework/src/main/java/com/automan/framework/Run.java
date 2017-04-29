package com.automan.framework;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automan.framework.page.BasePage;
import com.baidu.www.Index;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * Hello world!
 *
 */
public class Run 
{
    public static void main( String[] args ) throws ParseException, FileNotFoundException, YamlException
    {
    	
    	
    	
    	try{
    	    WebDriver driver = new FirefoxDriver();
    	    Index indexPage = new Index(driver);
    	    indexPage.get("https://www.baidu.com");
    	    indexPage.type(indexPage.searchKeyInputBox, "selenium webdriver");
    	    indexPage.click(indexPage.submitInputBox);
    	    if (indexPage.isPageLoadCompleted())
    	    	driver.quit();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	

    }
}
