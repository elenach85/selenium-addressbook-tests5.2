
package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.fw.ContactHelper;
import com.example.utils.SortedListOf;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matcher.*;
public class ContactModificationTests extends TestBase{
	
@Test(dataProvider="contactsFromCsvFile")
//@Test(dataProvider="randomValidContactGeneratorWithoutGroupName")	
	public void ModifySomeContactAllFields(ContactData contact) {
		//save old list
	app.navigateTo().mainPage();
	
	        SortedListOf<ContactData>oldContactList=app.getContactHelper().getContactsFromDB();
			 Random rnd=new Random();
			int index=rnd.nextInt(oldContactList.size()-1);
			//actions
			contact.setContact_id(oldContactList.get(index).getContact_id());
			app.getContactHelper().modifyContact(index,contact)
		.modifyContactGroup(index, contact);
		 //save new list
			SortedListOf<ContactData>newContactList=app.getContactHelper().getContactsFromDB();
			//compare
			assertThat(newContactList, equalTo(oldContactList.without(index).withAdded(contact)));
			
	}


}



