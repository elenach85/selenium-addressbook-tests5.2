package com.example.tests;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


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


		
	
		
	
	



