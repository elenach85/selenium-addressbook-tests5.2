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

import com.example.utils.SortedListOf;

public class ContactRemovalTests extends TestBase{
	@Test
	public void deleteSomeContact() {
		//save old list
		SortedListOf<ContactData>oldContactList=app.getContactHelper().getContactsFromDB();
		SortedListOf<ContactData>oldContactListFromUI=app.getContactHelper().getContactsFromUI();
		assertEquals(oldContactList.size(),oldContactListFromUI.size());
		assertEquals(String.valueOf(oldContactList.size()), app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
		assertEquals(oldContactList,oldContactListFromUI);
		 Random rnd=new Random();
		 int index=rnd.nextInt(oldContactList.size()-1);
		 String idFromDB=oldContactList.get(index).getId();
		//actions
		 app.getContactHelper().deleteContact(idFromDB);
		 //save new list
		 SortedListOf<ContactData>newContactList=app.getContactHelper().getContactsFromDB();
		 SortedListOf<ContactData>newContactListFromUI=app.getContactHelper().getContactsFromUI();
		  //compare
			assertThat(newContactList, equalTo(oldContactList.without(index)));
			assertEquals(String.valueOf(newContactList.size()),app.getDriver().findElement(By.xpath("//span[@id='search_count']")).getText());
			assertEquals(newContactList,newContactListFromUI);
		 
		
		
	 }


}
