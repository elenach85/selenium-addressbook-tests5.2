package com.example.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.fw.ApplicationManager;

public class Sample3 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileReader(new File("application.properties")));
		ApplicationManager app=new ApplicationManager(properties);
		app.navigateTo().printPhonesPage();
		List<WebElement> elements=app.getDriver().findElements(By.xpath("//table[@id='view']/tbody/tr/td[@valign='top']"));
		for (WebElement element : elements) {
			String name=element.findElement(By.tagName("b")).getText();	
			String text=element.getText();
			System.out.println(text);
			String extracttext=getHomeTel(text);
			System.out.println("regexResult"+ getHomeTel(text));	
		}
	}
	public static String getHomeTel(String text) {
		Pattern regex = Pattern.compile("H:\\s*\\w*[\\w*\\-*]+w*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		
		}
}
}