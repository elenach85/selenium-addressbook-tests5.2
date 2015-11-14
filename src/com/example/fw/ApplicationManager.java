package com.example.fw;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Properties;

public class ApplicationManager {
	private static WebDriver driver;
	public  String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	public NavigationHelper navigationHelper;
	public GroupHelper groupHelper;
	public ContactHelper contactHelper;
	private Properties properties;
	public HibernateHelper hibernateHelper;
	private ApplicationModel model;
	public  ApplicationManager(Properties properties){
		this.properties = properties;
		model= new ApplicationModel();
		model.setGroups(getHibernateHelper().listGroups());
		model.setContacts(getHibernateHelper().listContacts());
	}
		public void stop() {
		driver.quit();
	   	
	}
public ApplicationModel getModel(){
return model;	
}
public String getProperty(String key){
	return properties.getProperty(key);
	
}
public NavigationHelper navigateTo(){
	if (navigationHelper==null) {
	navigationHelper=new NavigationHelper(this);	
	}
	return navigationHelper;
}
public GroupHelper getGroupHelper(){
if (groupHelper==null) {
	groupHelper=new GroupHelper(this);
}
return groupHelper;
}
public ContactHelper getContactHelper(){
	if (contactHelper==null) {
	contactHelper=new ContactHelper(this);	
	}
	return contactHelper;
}
public HibernateHelper getHibernateHelper() {
	if (hibernateHelper==null) {
	hibernateHelper=new HibernateHelper(this);	
	}
	return hibernateHelper;	
}
public WebDriver getDriver() {
	String browser=properties.getProperty("browser");
	if (driver==null) {
		if ("firefox".equals(browser)) {
			driver = new FirefoxDriver();	
		} else 
		if ("ie".equals(browser)) {
			driver = new InternetExplorerDriver();	
		}
		else {
			throw new Error("Unsupported browser:"+ browser);
		}
		
		baseUrl=properties.getProperty("baseUrl");
	    driver.get(baseUrl);
		}
	return driver;
}


}

