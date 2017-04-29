package com.automan.framework.util;

import java.util.Map;

public class Locator {

	private String element;
	
	private long waitSecs;
	
	private ByType byType;
	
	private String what;
	
	public enum ByType{
		xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
	}
	
//	public Locator(){
//		
//	}
	
	public Locator(String name, Map map){
		this.element = name;
		this.what = (String) map.get("what");
		this.waitSecs = Long.valueOf((String) map.get("wait"));
		switch ((String) map.get("type")){
			case "xpath":
				this.byType = ByType.xpath;
				break;
			case "id":
				this.byType = ByType.id;
				break;
			case "name":
				this.byType = ByType.name;
				break;
			case "linkText":
				this.byType = ByType.linkText;
				break;
			case "className":
				this.byType = ByType.className;
				break;
			case "cssSelector":
				this.byType = ByType.cssSelector;
				break;
			case "partialLinkText":
				this.byType = ByType.partialLinkText;
				break;
			case "tagName":
				this.byType = ByType.tagName;
				break;
			default:
				this.byType = ByType.xpath;
		}

			
	}
	
//	public Locator(String element, long waitSecs){
//		this.element = element;
//		this.what = this.getClass().getCanonicalName();
//		this.waitSecs = waitSecs;
//		this.byType = byType.xpath;
//	}
//	
//	public Locator(String element, long waitSecs, ByType byType){
//		this.element = element;
//		this.what = super.getClass().getCanonicalName();
//		this.waitSecs = waitSecs;
//		this.byType = byType;
//	}
	
	public String getElement(){
		return this.element;
	}
	
	public void setElement(String element){
		this.element = element;
	}
	
	public long getWaitSecs(){
		return this.waitSecs;
	}
	
	public void setWaitSecs(long waitSecs){
		this.waitSecs = waitSecs;
	}
	
	public ByType getByType(){
		return this.byType;
	}
	
	public void setByType(ByType byType){
		this.byType = byType;
	}
	
	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}
}
