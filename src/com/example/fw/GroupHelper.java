package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import javax.swing.border.TitledBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

public class GroupHelper extends WebDriverHelperBase  {	

	public GroupHelper(ApplicationManager manager) {
		super(manager);
		}
	
	
	public SortedListOf<GroupData> getGroupsFromUI() {
		SortedListOf<GroupData>groups=new SortedListOf<GroupData>();
		manager.navigateTo().groupsPage();
			List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
			for (WebElement checkbox : checkboxes) {
			String title=checkbox.getAttribute("title");
			String name=title.substring("Select (".length(),title.length()-")".length());
			String id=checkbox.getAttribute("value");
			groups.add(new GroupData().withName(name).withId(id));	
		   
	}
			return groups;
		}
		
		public SortedListOf<GroupData> getGroupsFromDB() {
			 SortedListOf<GroupData>groups=new SortedListOf<GroupData>(manager.getHibernateHelper().listGroups());
			return groups;
			
			}
	public void createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		    initGroupCreation();
		  	fillGroupForm(group);
		    submitGroupCreation();
		    returnGroupsPage();
		    //update model
		    manager.getModel().addGroup(group);
		   
		
	}
	
	public void deleteGroup(int index) {
		manager.navigateTo().groupsPage();
		selectGroupByIndex(index);
		submitGroupRemoval();
		returnGroupsPage();
		//update model
		manager.getModel().removeGroup(index);
		}
	
	
	public void modifyGroup(String groupId, GroupData group,int index){
		manager.navigateTo().groupsPage();
	    initGroupModification(groupId);
	    fillGroupForm(group);
		submitGroupModification();
		 returnGroupsPage();
		 // update model
		manager.getModel().removeGroup(index).addGroup(group);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------		
	public  void initGroupCreation() {
	    click(By.name("new"));
	    
}
	
	public void submitGroupCreation() {
		    click(By.name("submit"));
		  
	}
	public void returnGroupsPage() {
	   click(By.linkText("group page"));
}
	public void fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getName());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());	
	}
	
	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]']["+ (index+1)+"]"));
 
	}
	
	private void selectGroupById(String groupId) {
		click(By.xpath("//input[@value='" + groupId + "']"));
 
	}
	public void initGroupModification(String groupId) {
	manager.navigateTo().groupsPage();
	selectGroupById(groupId);
	click(By.name("edit")); 
	}
	public void submitGroupModification() {
		click(By.name("update"));	
		  
	
	}
	
	public void submitGroupRemoval() {
		click(By.name("delete"));
		 
	}
	
}
