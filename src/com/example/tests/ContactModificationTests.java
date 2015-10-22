
package com.example.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
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
//@Test(dataProvider="randomValidContactGeneratorWithoutGroupName")	
	public void ModifySomeContactAllFields(ContactData contact) {
		//save old list
	app.navigateTo().mainPage();
	        SortedListOf<ContactData>oldContactList=app.getContactHelper().getContactsFromDB();
	        System.out.println(oldContactList); 
			 Random rnd=new Random();
			int index=rnd.nextInt(oldContactList.size()-1);
			System.out.println("index=:" + index);
			//read data from Edit form before modification
			
			app.getContactHelper().selectContact(index);
			String firstNameFromEditForm=getValueFromField(By.name("firstname"));
			System.out.println("firstnameFromEditForm:"+ firstNameFromEditForm);
			String lastNameFromEditForm=getValueFromField(By.name("lastname"));
			System.out.println("lastnameFromEditForm:"+ lastNameFromEditForm);
			String addressFromEditForm = getTextFromField(By.name("address"));
			System.out.println("addressFromEditForm:"+ addressFromEditForm);
			String hometelFromEditForm=getValueFromField(By.name("home"));
			System.out.println("hometelFromEditForm:"+ hometelFromEditForm);
			String mobiletelFromEditForm=getValueFromField(By.name("mobile"));
			System.out.println("mobiletelFromEditForm:"+ mobiletelFromEditForm);
			String worktelFromEditForm=getValueFromField(By.name("work"));
			System.out.println("worktelFromEditForm:"+ worktelFromEditForm);
			String emailFromEditForm=getValueFromField(By.name("email"));
			System.out.println("emailFromEditForm:"+ emailFromEditForm);
			String email2FromEditForm=getValueFromField(By.name("email2"));
			System.out.println("email2FromEditForm:"+ email2FromEditForm);
			String bdayFromEditForm=getValueFromField(By.name("bday"));
			System.out.println("bdayFromEditForm:"+ bdayFromEditForm);
			String bmonthFromEditForm=getValueFromField(By.name("bmonth"));
			System.out.println("bmonthFromEditForm:"+ bmonthFromEditForm);
			String byearFromEditForm=getValueFromField(By.name("byear"));
			System.out.println("byearFromEditForm:"+ byearFromEditForm);
			String address2FromEditForm = getTextFromField(By.name("address2"));
			System.out.println("address2FromEditForm:"+ address2FromEditForm);
			String phone2FromEditForm = getValueFromField(By.name("phone2"));
			System.out.println("phone2FromEditForm:"+ phone2FromEditForm);
			
			//read data from database before modification
			
		    String firstNameFromDB =oldContactList.get(index).getFirst_name();
		    System.out.println("firstnameFromDB:"+ firstNameFromDB);
			String lastNameFromDB=oldContactList.get(index).getLast_name();
			System.out.println("lastnameFromDB:"+ lastNameFromDB);
			String addressFromDB=oldContactList.get(index).getAddress_1();
			System.out.println("addressFromDB:"+addressFromDB);
			String hometelFromDB=oldContactList.get(index).getHome_tel();
			System.out.println("hometelFromDB:"+ hometelFromDB);
			String worktelFromDB=oldContactList.get(index).getWork_tel();
			System.out.println("worktelFromDB:"+ worktelFromDB);
			String mobiletelFromDB=oldContactList.get(index).getMobile_tel();
			System.out.println("mobiletelFromDB:"+ mobiletelFromDB);
			String emailFromDB=oldContactList.get(index).getEmail();
			System.out.println("emailFromDB:"+ emailFromDB);
			String email2FromDB=oldContactList.get(index).getEmail2();
			System.out.println("email2FromDB:"+ email2FromDB);
			String bdayFromDB=oldContactList.get(index).getBirth_day();
			System.out.println("bdayFromDB:"+ bdayFromDB);
			String bmonthFromDB=oldContactList.get(index).getBirth_month();
			System.out.println("bmonthFromDB:"+ bmonthFromDB);
			String byearFromDB=oldContactList.get(index).getBirth_year();
			System.out.println("byearFromDB:"+ byearFromDB);
			String address2FromDB=oldContactList.get(index).getAddress_2();
			System.out.println("address2FromDB:"+ address2FromDB);
			String phone2FromDB=oldContactList.get(index).getPhone_2();
			System.out.println("phone2FromDB:"+ phone2FromDB);
			String idFromDB=oldContactList.get(index).getId();
			System.out.println(idFromDB);
			
			//compare data from Edit Form and from database
			
			compareContactData(firstNameFromEditForm, lastNameFromEditForm, addressFromEditForm, hometelFromEditForm,
					mobiletelFromEditForm, worktelFromEditForm, emailFromEditForm, email2FromEditForm, bdayFromEditForm,
					bmonthFromEditForm, byearFromEditForm, address2FromEditForm, phone2FromEditForm, firstNameFromDB,
					lastNameFromDB, addressFromDB, hometelFromDB, worktelFromDB, mobiletelFromDB, emailFromDB,
					email2FromDB, bdayFromDB, bmonthFromDB, byearFromDB, address2FromDB, phone2FromDB);
			
			
			//actions 
			
		    contact.setContact_id(oldContactList.get(index).getContact_id());
		    app.getContactHelper().modifyContact(index,contact)
		.modifyContactGroup(index, contact);
		    
		    //open modified contact,read data from Edit Form
		    
			app.getDriver().findElement((By.xpath("//a[@href='edit.php?id=" + idFromDB + "']"))).click();
			String firstNameFromEditFormAfterModification=getValueFromField(By.name("firstname"));
			System.out.println("firstnameFromEditFormAfterModification:"+ firstNameFromEditFormAfterModification);
			String lastNameFromEditFormAfterModification=getValueFromField(By.name("lastname"));
			System.out.println("lastnameFromEditFormAfterModification:"+ lastNameFromEditFormAfterModification);
			String addressFromEditFormAfterModification = getTextFromField(By.name("address"));
			System.out.println("addressFromEditFormAfterModification:"+ addressFromEditFormAfterModification);
			String hometelFromEditFormAfterModification=getValueFromField(By.name("home"));
			System.out.println("hometelFromEditFormAfterModification:"+ hometelFromEditFormAfterModification);
			String mobiletelFromEditFormAfterModification=getValueFromField(By.name("mobile"));
			System.out.println("mobiletelFromEditFormAfterModification:"+ mobiletelFromEditFormAfterModification);
			String worktelFromEditFormAfterModification=getValueFromField(By.name("work"));
			System.out.println("worktelFromEditFormAfterModification:"+ worktelFromEditFormAfterModification);
			String emailFromEditFormAfterModification=getValueFromField(By.name("email"));
			System.out.println("emailFromEditFormAfterModification:"+ emailFromEditFormAfterModification);
			String email2FromEditFormAfterModification=getValueFromField(By.name("email2"));
			System.out.println("email2FromEditFormAfterModification:"+ email2FromEditFormAfterModification);
			String bdayFromEditFormAfterModification=getValueFromField(By.name("bday"));
			System.out.println("bdayFromEditFormAfterModification:"+ bdayFromEditFormAfterModification);
			String bmonthFromEditFormAfterModification=getValueFromField(By.name("bmonth"));
			System.out.println("bmonthFromEditFormAfterModification:"+ bmonthFromEditFormAfterModification);
			String byearFromEditFormAfterModification=getValueFromField(By.name("byear"));
			System.out.println("byearFromEditFormAfterModification:"+ byearFromEditFormAfterModification);
			String address2FromEditFormAfterModification = getTextFromField(By.name("address2"));
			System.out.println("address2FromEditFormAfterModification:"+ address2FromEditFormAfterModification);
			String phone2FromEditFormAfterModification = getValueFromField(By.name("phone2"));
			System.out.println("phone2FromEditFormAfterModification:"+ phone2FromEditFormAfterModification);
			
			
			//save new list
			SortedListOf<ContactData>newContactList=app.getContactHelper().getContactsFromDB();
			System.out.println(app.getContactHelper().getContactsFromDB());

			//get modified contact from newContactlist
			
			int indexfromnewlist=newContactList.indexOf(contact);
			//String indexfromnewlistString=String.valueOf(newContactList.indexOf(contact));
			System.out.println(indexfromnewlist);
			System.out.println("checkId:"+ newContactList.get(indexfromnewlist).getId());
			//read data from database after modification
			String firstNameFromDBAfterModification =newContactList.get(indexfromnewlist).getFirst_name(); 
			System.out.println("first name:"+ firstNameFromDBAfterModification);
			String lastNameFromDBAfterModification=newContactList.get(indexfromnewlist).getLast_name();
			System.out.println("last name:" + lastNameFromDBAfterModification);
			String addressFromDBAfterModification=newContactList.get(indexfromnewlist).getAddress_1();
			System.out.println("address:"+ addressFromDBAfterModification);
			String hometelFromDBAfterModification=newContactList.get(indexfromnewlist).getHome_tel();
			System.out.println("hometel:"+ hometelFromDBAfterModification);
			String worktelFromDBAfterModification=newContactList.get(indexfromnewlist).getWork_tel();
			System.out.println("worktel:"+ worktelFromDBAfterModification);
			String mobiletelFromDBAfterModification=newContactList.get(indexfromnewlist).getMobile_tel();
			System.out.println("mobiletel:"+ mobiletelFromDBAfterModification);
			String emailFromDBAfterModification=newContactList.get(indexfromnewlist).getEmail();
			System.out.println("email:"+ emailFromDBAfterModification);
			String email2FromDBAfterModification=newContactList.get(indexfromnewlist).getEmail2();
			System.out.println("email2:"+ email2FromDBAfterModification);
			String bdayFromDBAfterModification=newContactList.get(indexfromnewlist).getBirth_day();
			System.out.println("bday:"+ bdayFromDBAfterModification);
			String bmonthFromDBAfterModification=newContactList.get(indexfromnewlist).getBirth_month();
			System.out.println("bmonth:"+ bmonthFromDBAfterModification);
			String byearFromDBAfterModification=newContactList.get(indexfromnewlist).getBirth_year();
			System.out.println("byear:"+ byearFromDBAfterModification);
			String address2FromDBAfterModification=newContactList.get(indexfromnewlist).getAddress_2();
			System.out.println("address2:"+ address2FromDBAfterModification);
			String phone2FromDBAfterModification=newContactList.get(indexfromnewlist).getPhone_2();
			System.out.println("phone2:"+ phone2FromDBAfterModification);
			
			//compare contact data for modified contact
			
			compareContactData(firstNameFromEditFormAfterModification, lastNameFromEditFormAfterModification, addressFromEditFormAfterModification, hometelFromEditFormAfterModification,
					mobiletelFromEditFormAfterModification, worktelFromEditFormAfterModification, emailFromEditFormAfterModification, email2FromEditFormAfterModification, bdayFromEditFormAfterModification,
					bmonthFromEditFormAfterModification, byearFromEditFormAfterModification, address2FromEditFormAfterModification, phone2FromEditFormAfterModification, firstNameFromDBAfterModification,
					lastNameFromDBAfterModification, addressFromDBAfterModification, hometelFromDBAfterModification, worktelFromDBAfterModification, mobiletelFromDBAfterModification, emailFromDBAfterModification,
					email2FromDBAfterModification, bdayFromDBAfterModification, bmonthFromDBAfterModification, byearFromDBAfterModification,address2FromDBAfterModification, phone2FromDBAfterModification);
			
			//compare lists
			assertThat(newContactList, equalTo(oldContactList.without(index).withAdded(contact)));
			
	}







}



