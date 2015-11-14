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

public class GroupModificationTests extends TestBase {
	 @Test(dataProvider="groupsFromXmlFile")
public void modifySomeGroup(GroupData group) {
		 //save old state
		 app.navigateTo().groupsPage();
		SortedListOf<GroupData>oldList=app.getModel().getGroups();
		 Random rnd=new Random();
		 int index=rnd.nextInt(oldList.size()-1);
		String groupId=oldList.get(index).getId();
	if (wantToCheckBeforeModification())
		
	{
		if ("yes".equals(app.getProperty("check.ui"))) {
			app.getGroupHelper().initGroupModification(groupId);
			//get data from GroupEdit page 
			GroupData groupBeforeModificationUI=new GroupData().withFooter(getTextFromField(By.name("group_footer"))).withHeader(getTextFromField(By.name("group_header"))).withName(getValueFromField(By.name("group_name"))).withId(groupId);
			GroupData groupFromModel=oldList.get(index);
			assertEquals(groupFromModel, groupBeforeModificationUI);
		}
		if ("yes".equals(app.getProperty("check.db"))) {
			GroupData groupFromModel=oldList.get(index);
			GroupData groupToBeModifiedFromDB=app.getHibernateHelper().getGroup(groupId);
			assertEquals(groupFromModel, groupToBeModifiedFromDB);
			  }	
	}
	group.setId(groupId);
		app.getGroupHelper().modifyGroup(groupId,group,index);
		////save new state
		 SortedListOf<GroupData>newList=app.getModel().getGroups();
		if (wantToCheckAfterModification())
		{
			if ("yes".equals(app.getProperty("check.ui"))) {
		app.getGroupHelper().initGroupModification(groupId);
		 //open modified group,read data from Edit Form
		GroupData groupAfterModification=new GroupData().withFooter(getTextFromField(By.name("group_footer"))).withHeader(getTextFromField(By.name("group_header"))).withName(getValueFromField(By.name("group_name"))).withId(groupId);
		//check that this group presents in Model
	    assertEquals(newList.contains(groupAfterModification), true);
			}
			if ("yes".equals(app.getProperty("check.db"))) {
		GroupData groupWasModifiedFromDB=app.getHibernateHelper().getGroup(groupId);
		  assertEquals(newList.contains(groupWasModifiedFromDB), true);
		}
		}
		 //compare states
		 assertThat(newList,equalTo(oldList.without(index).withAdded(group)));
		 if (wantToCheck()) {
				System.out.println("check lists!!!");
				if ("yes".equals(app.getProperty("check.db"))) {
					assertThat(app.getModel().getGroups(),equalTo(app.getHibernateHelper().listGroups()));	
				}
				if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getGroups(),equalTo(app.getGroupHelper().getGroupsFromUI()));
				  }	
			}
			
	 }

}
