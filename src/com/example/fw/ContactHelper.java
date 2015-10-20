 package com.example.fw;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class ContactHelper extends WebDriverHelperBase {
	public static boolean CREATION=true;
	public static boolean MODIFICATION=false;
	private List<WebElement> groupNames;

	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	private SortedListOf<ContactData>cachedContacts;
	
	public SortedListOf<ContactData> getContacts() {
		if (cachedContacts==null) {
			rebuildCache();
		}
		return cachedContacts;
		}
		
	private void rebuildCache() {
	cachedContacts=new SortedListOf<ContactData>();
		List<WebElement> rows = findElements();
		for (WebElement row : rows) {
			List<WebElement>columns =row.findElements(By.tagName("td"));
			ContactData contact=new ContactData();
			String last_name_title=columns.get(1).getText();
			String first_name_title=columns.get(2).getText();
			String email=columns.get(3).getText();
			String home_tel=columns.get(4).getText();
			String id=columns.get(0).findElement(By.xpath(".//input[@value]")).getAttribute("value");
	cachedContacts.add(new ContactData().withFirstname(first_name_title).withLastname(last_name_title).withEmail(email).withHomeTel(home_tel).withId(id));

		}
		}

	public void createContact(ContactData contact) {
		initContactCreation();
		randomGroupSelection(CREATION);
		fillContactForm(contact,CREATION);
		submitContactCreation();
	    manager.navigateTo().mainPage();
	    rebuildCache();
		
	}

	public ContactHelper modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		selectContact(index);
		fillContactForm(contact,MODIFICATION);
	    submitContactModification();
		manager.navigateTo().mainPage();
		rebuildCache();
		return this;
	}
	public void modifyContactGroup(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		checkContact(index);
	    randomGroupSelection(MODIFICATION);
		submitGroupChange();
		manager.navigateTo().goToSubmittedGroupPage();
		manager.navigateTo().mainPage();
		resetGroupsListToAllValue();
		
	}
	public void deleteContact(int index) {
		selectContact(index);
		deleteContact();
		manager.navigateTo().mainPage();
		rebuildCache();
		
	}
	

	
	
	
//-------------------------------------------------------------------------------------------------------------------------	
	
	
	
	public void initContactCreation() {
		if (!manager.navigationHelper.onCreateContactPage()){
			driver.findElement(By.linkText("add new")).click();
			
		}
	
	}
	
	public void fillContactForm(ContactData contact,boolean formType) {
		if(formType==CREATION)
		{
		selectByText(By.name("new_group"),contact.getGroup_name());	
		}
		type(By.name("firstname"),contact.getFirst_name());
		type(By.name("lastname"),contact.getLast_name());
	  	type(By.name("address"),contact.getAddress_1());
		type(By.name("home"),contact.getHome_tel());
	  	type(By.name("mobile"),contact.getMobile_tel());
	    type(By.name("work"),contact.getWork_tel());
		type(By.name("email"),contact.getEmail());
		type(By.name("email2"),contact.getEmail2());
		type(By.name("byear"),contact.getBirth_year());
	   	selectByText(By.name("bmonth"), contact.getBirth_month());
	   	selectByText(By.name("bday"), contact.getBirth_day());
	    type(By.name("address2"),contact.getAddress_2());
	    type(By.name("phone2"),contact.getPhone_2());
	
	  
	}
	
	public void submitContactCreation() {
		driver.findElement(By.name("submit")).click();
	
	}

	
	public void selectContact(int index) {
	click(By.xpath("//tr[@name='entry'][" + (index+1) + "]/td/a/img[@title='Edit']"));

	}

	public void deleteContact() {
	click(By.xpath("//input[@value='Delete']"));
	
	}
	public void checkContact(int index) {
		if (manager.navigationHelper.onMainPage()==true) {
	    click(By.xpath("(//input[@type='checkbox'])[" + (index+1) + "]"));
		}
		else manager.navigateTo().mainPage();
		 click(By.xpath("(//input[@type='checkbox'])[" + (index+1) + "]"));
		}
	  public void submitContactModification() {
		  click(By.xpath("//input[@value='Update']"));
	}

	public void submitGroupChange() {
	click(By.xpath("//input[@name='add']"));
		
	}

	private List<WebElement> findElements() {
		return driver.findElements(By.name("entry"));
	}
	public String randomGroupSelection(boolean formType){
//SortedListOf<GroupData> groupNameList = manager.getGroupHelper().getGroups();
List<GroupData> groupNameList = manager.getHibernateHelper().listGroups();
		//List<WebElement> groupNamesList=getGroupsNameList();
		Random rnd=new Random();	
		int index2=rnd.nextInt(groupNameList.size()-1);
		//manager.navigateTo().onMainPage();
		if (formType==MODIFICATION)
	       {
		selectGroupForChange(index2);
	       }
	else {
	//	initContactCreation();
	selectGroupForContactCreation(index2);		
	}
		String groupName;
		groupName=groupNameList.get(index2).getName();
		System.out.println(groupName);
		 return groupName;
	}
	/*public List<WebElement> getGroupsNameList(){
		if (manager.navigationHelper.onCreateContactPage()==true) {
			List<WebElement>groupNames=driver.findElements(By.xpath("//select[@name='new_group']/option"));
			return groupNames;
		} else {if (manager.navigationHelper.onMainPage()==true) {
			List<WebElement>groupNames=driver.findElements(By.xpath("//select[@name='to_group']/option"));
			return groupNames;	
		}
		return groupNames;		
		}
		}		
	public List<GroupData> getGroups() {
 		SortedListOf<GroupData> groups= new SortedListOf<GroupData>();
 		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
 		for (WebElement checkbox : checkboxes) {
        GroupData group=new GroupData();
 		String title=checkbox.getAttribute("title");
			group.name=title.substring("Select (".length(),title.length()-")".length());
			groups.add(group);				
			String name=title.substring("Select (".length(),title.length()-")".length());
		groups.add(new GroupData().withName(name));	
 		}
 		return groups;
 	}
 	*/
	public void selectGroupForContactCreation(int index2) {
		
	     click(By.xpath("(//select[@name='new_group']/option)[" + (index2+1) + "]"));
	  			  
		}
	
	public void selectGroupForChange(int index2) {
	      click(By.xpath("(//select[@name='to_group']/option)[" + (index2+1) + "]"));		  
		}
			  
			  
	public WebElement selectId(int index)	{
	WebElement id = driver.findElement(By.xpath("(//input[@id])[index+1]"));
	return id;
	}	
	public void resetGroupsListToAllValue() {
		 click(By.xpath("//select[@onchange='this.parentNode.submit()']/option[@value=''] "));	
		
	}	

}

