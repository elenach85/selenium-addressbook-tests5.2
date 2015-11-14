
package com.example.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.fw.ContactHelper;
import com.example.utils.SortedListOf;
import static org.hamcrest.Matcher.*;
public class ContactModificationTests extends TestBase{
	
@Test(dataProvider="contactsFromCsvFile")
	public void ModifySomeContactAllFields(ContactData contact) {
		//save old list
	app.navigateTo().mainPage();
	       SortedListOf<ContactData>oldContactList=app.getModel().getContacts();
	   	assertEquals(String.valueOf(oldContactList.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
			 Random rnd=new Random();
			int index=rnd.nextInt(oldContactList.size()-1);
			String idFromDB=oldContactList.get(index).getId();
			if (wantToCheckBeforeModification())
				
			{
				if ("yes".equals(app.getProperty("check.ui"))) {
					app.getContactHelper().selectContact(idFromDB);
					//get data from initContactEdit page 
					ContactData contactBeforeModificationUI=new ContactData().withFirstname(getValueFromField(By.name("firstname"))).withLastname(getValueFromField(By.name("lastname"))).withAddress1(getTextFromField(By.name("address"))).withHomeTel(getValueFromField(By.name("home"))).withMobile_Tel(getValueFromField(By.name("mobile"))).withWorktel(getValueFromField(By.name("work")))
							.withEmail(getValueFromField(By.name("email"))).withEmail2(getValueFromField(By.name("email2"))).withBirthDay(getValueFromField(By.name("bday"))).withBirthMonth(getValueFromField(By.name("bmonth"))).withBirthYear(getValueFromField(By.name("byear"))).withAddress2(getTextFromField(By.name("address2"))).withPhone2(getValueFromField(By.name("phone2"))).withId(idFromDB);
					ContactData contactFromModel=oldContactList.get(index);
					assertEquals(contactFromModel, contactBeforeModificationUI);
				}
				if ("yes".equals(app.getProperty("check.db"))) {
					ContactData contactFromModel=oldContactList.get(index);
					ContactData contactToBeModifiedFromDB=app.getHibernateHelper().getContact(idFromDB);
					assertEquals(contactFromModel, contactToBeModifiedFromDB);
					  }	
			}
			
		contact.setContact_id(idFromDB);
		  app.getContactHelper().modifyContact(idFromDB,index, contact)
		.modifyContactGroup(idFromDB);
		//save new list
			SortedListOf<ContactData>newContactList=app.getModel().getContacts();
			if (wantToCheckAfterModification())
			{
				if ("yes".equals(app.getProperty("check.ui"))) {
			 //open modified contact,read data from Edit Form
					app.getContactHelper().selectContact(idFromDB);
					ContactData contactAfterModificationUI=new ContactData().withFirstname(getValueFromField(By.name("firstname"))).withLastname(getValueFromField(By.name("lastname"))).withAddress1(getTextFromField(By.name("address"))).withHomeTel(getValueFromField(By.name("home"))).withMobile_Tel(getValueFromField(By.name("mobile"))).withWorktel(getValueFromField(By.name("work")))
							.withEmail(getValueFromField(By.name("email"))).withEmail2(getValueFromField(By.name("email2"))).withBirthDay(getValueFromField(By.name("bday"))).withBirthMonth(getValueFromField(By.name("bmonth"))).withBirthYear(getValueFromField(By.name("byear"))).withAddress2(getTextFromField(By.name("address2"))).withPhone2(getValueFromField(By.name("phone2"))).withId(idFromDB);
			//check that this contact presents in Model
		    assertEquals(newContactList.contains(contactAfterModificationUI), true);
				}
				if ("yes".equals(app.getProperty("check.db"))) {
			ContactData contactWasModifiedFromDB=app.getHibernateHelper().getContact(idFromDB);
			  assertEquals(newContactList.contains(contactWasModifiedFromDB), true);
			}
			}
			//compare lists
			assertThat(newContactList, equalTo(oldContactList.without(index).withAdded(contact)));
			if (wantToCheck()) {
				if ("yes".equals(app.getProperty("check.db"))) {
					assertThat(app.getModel().getContacts(),equalTo(app.getHibernateHelper().listContacts()));
					app.navigateTo().mainPage();
					assertEquals(String.valueOf(app.getHibernateHelper().listContacts().size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
				}
				if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getContacts(),equalTo(app.getContactHelper().getContactsFromUI()));
				assertEquals(String.valueOf(app.getContactHelper().getContactsFromUI().size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
				  }	
			
	}

}
}



