
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
	       SortedListOf<ContactData>oldContactList=app.getContactHelper().getContactsFromDB();
			 Random rnd=new Random();
			int index=rnd.nextInt(oldContactList.size()-1);
			String idFromDB=oldContactList.get(index).getId();
			ContactData contactToBeModifiedFromDB=app.getHibernateHelper().getContact(idFromDB);
			//read data from Edit form before modification
			app.getContactHelper().selectContact(idFromDB);
		
			ContactData contactFromEditForm=new ContactData().withFirstname(getValueFromField(By.name("firstname"))).withLastname(getValueFromField(By.name("lastname"))).withAddress1(getTextFromField(By.name("address"))).withHomeTel(getValueFromField(By.name("home"))).withMobile_Tel(getValueFromField(By.name("mobile"))).withWorktel(getValueFromField(By.name("work")))
					.withEmail(getValueFromField(By.name("email"))).withEmail2(getValueFromField(By.name("email2"))).withBirthDay(getValueFromField(By.name("bday"))).withBirthMonth(getValueFromField(By.name("bmonth"))).withBirthYear(getValueFromField(By.name("byear"))).withAddress2(getTextFromField(By.name("address2"))).withPhone2(getValueFromField(By.name("phone2"))).withId(idFromDB);
			assertEquals(contactToBeModifiedFromDB, contactFromEditForm);
			
		contact.setContact_id(idFromDB);
		  app.getContactHelper().modifyContact(idFromDB,contact)
		.modifyContactGroup(idFromDB);
		    
		    //open modified contact,read data from Edit Form
		    
			app.getContactHelper().selectContact(idFromDB);
			ContactData contactFromEditFormAfterModification=new ContactData().withFirstname(getValueFromField(By.name("firstname"))).withLastname(getValueFromField(By.name("lastname"))).withAddress1(getTextFromField(By.name("address"))).withHomeTel(getValueFromField(By.name("home"))).withMobile_Tel(getValueFromField(By.name("mobile"))).withWorktel(getValueFromField(By.name("work")))
			.withEmail(getValueFromField(By.name("email"))).withEmail2(getValueFromField(By.name("email2"))).withBirthDay(getValueFromField(By.name("bday"))).withBirthMonth(getValueFromField(By.name("bmonth"))).withBirthYear(getValueFromField(By.name("byear"))).withAddress2(getTextFromField(By.name("address2"))).withPhone2(getValueFromField(By.name("phone2"))).withId(idFromDB);
			//save new list
			SortedListOf<ContactData>newContactList=app.getContactHelper().getContactsFromDB();
			ContactData contactWasModifiedFromDB=app.getHibernateHelper().getContact(idFromDB);
			assertEquals(contactWasModifiedFromDB, contactFromEditFormAfterModification);
			//compare lists
			assertThat(newContactList, equalTo(oldContactList.without(index).withAdded(contact)));
			
	}







}



