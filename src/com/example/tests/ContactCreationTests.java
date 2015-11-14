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
		SortedListOf<ContactData>oldContactList=app.getModel().getContacts();
		assertEquals(String.valueOf(oldContactList.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
		//compare model with list from DB and UI
		if (wantToCheckBeforeModification())
		{
			if ("yes".equals(app.getProperty("check.ui"))) {
				SortedListOf<ContactData>oldContactListFromUI=app.getContactHelper().getContactsFromUI();
				assertEquals(oldContactList,oldContactListFromUI);
				assertEquals(String.valueOf(oldContactListFromUI.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
			}
			if ("yes".equals(app.getProperty("check.db"))) {
				SortedListOf<ContactData>oldContactListFromDB=(SortedListOf<ContactData>) app.getHibernateHelper().listContacts();
				assertEquals(oldContactList,oldContactListFromDB);
				assertEquals(String.valueOf(oldContactListFromDB.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
			}
		}
		
		//actions
	app.getContactHelper().createContact(contact);
	app.navigateTo().mainPage();
	//save new list
	SortedListOf<ContactData>newContactList=app.getModel().getContacts();
	assertEquals(String.valueOf(newContactList.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
	assertThat(newContactList,equalTo(oldContactList.withAdded(contact)));
	if (wantToCheck()) {
		if ("yes".equals(app.getProperty("check.db"))) {
			assertThat(app.getModel().getContacts(),equalTo(app.getHibernateHelper().listContacts()));
			assertEquals(String.valueOf(app.getHibernateHelper().listContacts().size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
		}
		if ("yes".equals(app.getProperty("check.ui"))) {
		assertThat(app.getModel().getContacts(),equalTo(app.getContactHelper().getContactsFromUI()));
		assertEquals(String.valueOf(app.getContactHelper().getContactsFromUI().size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
		  }	
	}
	
}

	
}


		
	
		
	
	



