package com.example.tests;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class PrintPhonesTests extends TestBase{
	@Test
	public void CheckPrintPhonesList(){
app.navigateTo().mainPage();
SortedListOf<ContactDataForPrintPhones>contactsFromMainPage=app.getContactHelper().getContactsFromUIForPrPhonesFromMainPage();
	app.navigateTo().printPhonesPage();
	SortedListOf<ContactDataForPrintPhones>contactsFromPrintPhonesPage=app.getContactHelper().getContactsFromUIForPrintPhones();
assertThat(contactsFromMainPage,equalTo(contactsFromPrintPhonesPage));
	}
		   
}	
		
	
		
	
