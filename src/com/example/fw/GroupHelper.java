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
	
	private SortedListOf<GroupData>cachedGroups;
	
	public SortedListOf<GroupData> getGroupsFromUI() {
		if (cachedGroups==null){
		rebuildCacheFromUI();
		}
		return cachedGroups;
		}
	
		private void rebuildCacheFromUI() {
			cachedGroups= new SortedListOf<GroupData>();
			   manager.navigateTo().groupsPage();
			List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
			for (WebElement checkbox : checkboxes) {
			String title=checkbox.getAttribute("title");
				String name=title.substring("Select (".length(),title.length()-")".length());
				cachedGroups.add(new GroupData().withName(name));	
		
	}
		}
		
		public SortedListOf<GroupData> getGroupsFromDB() {
			if (cachedGroups==null){
			rebuildCacheFromDB();
			}
			return cachedGroups;
			}
		
			private void rebuildCacheFromDB() {
				cachedGroups= new SortedListOf<GroupData>(manager.getHibernateHelper().listGroups());
			
			}
	public void createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		    initGroupCreation();
		  	fillGroupForm(group);
		    submitGroupCreation();
		    returnGroupsPage();
		    rebuildCacheFromDB();
		   
		
	}
	
	public void deleteGroup(int index) {
		manager.navigateTo().groupsPage();
		selectGroupByIndex(index);
		submitGroupRemoval();
		returnGroupsPage();
		rebuildCacheFromDB();
		}
	
	
	public void modifyGroup(int index, GroupData group){
		manager.navigateTo().groupsPage();
	    initGroupModification(index);
	    fillGroupForm(group);
		submitGroupModification();
		 returnGroupsPage();
		rebuildCacheFromDB();	
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
	public void initGroupModification(int index) {
	selectGroupByIndex(index);
	click(By.name("edit")); 
	}
	public void submitGroupModification() {
		click(By.name("update"));	
		  
	
	}
	
	public void submitGroupRemoval() {
		click(By.name("delete"));
		 
	}
	
}
