package com.example.tests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import static org.testng.Assert.assertEquals;
import static com.example.tests.ContactDataGenerator.generateRandomContacts;
import static com.example.tests.ContactDataGenerator.loadContactsFromCsvFile;
import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.fw.ContactHelper;
import com.example.utils.ListOf;
import com.example.utils.SortedListOf;

public class ContactCreationTests extends TestBase {
	

	@Test(dataProvider="contactsFromXmlFile")
public void testContactCreationWithValidData(ContactData contact) throws Exception{ 
		app.navigateTo().mainPage();	
		// save old list
		SortedListOf<ContactData>oldContactList=app.getContactHelper().getContactsFromDB();
		SortedListOf<ContactData>oldContactListFromUI=app.getContactHelper().getContactsFromUI();
		assertEquals(oldContactList.size(),oldContactListFromUI.size());
		assertEquals(String.valueOf(oldContactList.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
		assertEquals(oldContactList,oldContactListFromUI);
		//actions
	app.getContactHelper().createContact(contact);
		//save new list
	  SortedListOf<ContactData>newContactList=app.getContactHelper().getContactsFromDB();
	  SortedListOf<ContactData>newContactListFromUI=app.getContactHelper().getContactsFromUI();
		assertThat(newContactList, equalTo(oldContactList.withAdded(contact)));
		assertEquals(String.valueOf(newContactList.size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
		assertEquals(newContactList,newContactListFromUI);
}

	
}


		
	
		
	
	



