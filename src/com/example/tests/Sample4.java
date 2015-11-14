package com.example.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.fw.ApplicationManager;
import com.example.utils.SortedListOf;

public class Sample4 extends TestBase {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileReader(new File("application.properties")));
		ApplicationManager app=new ApplicationManager(properties);
		String num=app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText();
		System.out.println(num);
		assertEquals(String.valueOf(app.getHibernateHelper().listContacts().size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
	
}
}

