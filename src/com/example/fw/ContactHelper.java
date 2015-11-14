package com.example.fw;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.tests.ContactDataForPrintPhones;
import com.example.tests.GroupData;
import com.example.utils.SortedListOf;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

public class ContactHelper extends WebDriverHelperBase {
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	private SortedListOf<ContactData> contacts;

	public SortedListOf<ContactData> getContactsFromUI() {
		manager.navigateTo().mainPage();
		contacts=new SortedListOf<ContactData>();
		List<WebElement> rows = findElements();
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			ContactData contact = new ContactData();
			String last_name_title = columns.get(1).getText();
			String first_name_title = columns.get(2).getText();
			String email = columns.get(3).getText();
			String home_tel = columns.get(4).getText();
			String id = columns.get(0).findElement(By.xpath(".//input[@value]")).getAttribute("value");
			contacts.add(new ContactData().withFirstname(first_name_title).withLastname(last_name_title)
					.withEmail(email).withHomeTel(home_tel).withId(id));

		}
		return contacts;
	}


	public SortedListOf<ContactDataForPrintPhones> getContactsFromUIForPrintPhones() {
		SortedListOf<ContactDataForPrintPhones>contactsForPrPhones=new SortedListOf<ContactDataForPrintPhones>();
		List<WebElement> elements=manager.getDriver().findElements(By.xpath("//table[@id='view']/tbody/tr/td[@valign='top']"));
		for (WebElement element : elements) {
			String name=element.findElement(By.tagName("b")).getText();	
			String text=element.getText();
			String extractHome=getHomeTel(text);
			String home_tel;
			if (extractHome.equals("")) {
				home_tel="";
			} else {
				home_tel=extractHome.substring("H: ".length(), (extractHome.length()-"\n".length()));
			}
			contactsForPrPhones.add(new ContactDataForPrintPhones().withUnitedName(name).withHome_Tel(home_tel));
		}
		return contactsForPrPhones;	
		
	}
	public SortedListOf<ContactDataForPrintPhones> getContactsFromUIForPrPhonesFromMainPage() {
		SortedListOf<ContactDataForPrintPhones>contactsFromMainPage = new SortedListOf<ContactDataForPrintPhones>();
		List<WebElement> rows = findElements();
		for (WebElement row : rows) {
			String unitedName;	
			List<WebElement> columns = row.findElements(By.tagName("td"));
			String last_name_title = columns.get(1).getText();
			String first_name_title = columns.get(2).getText();
			String home_tel = columns.get(4).getText();
			if (first_name_title.equals("")) {
				unitedName=last_name_title;
			} else {
				unitedName=first_name_title.concat(" "+ last_name_title);
			}
			
			contactsFromMainPage.add(new ContactDataForPrintPhones().withUnitedName(unitedName).withHome_Tel(home_tel));

		}
		return contactsFromMainPage;
	
	}
	public SortedListOf<ContactData> getContactsFromDB() {
	return contacts= new SortedListOf<ContactData>(manager.getHibernateHelper().listContacts());	
	}


	public void createContact(ContactData contact) {
		initContactCreation();
		randomGroupSelection(CREATION);
		fillContactForm(contact, CREATION);
		submitContactCreation();
		manager.navigateTo().mainPage();
		 //update model
	    manager.getModel().addContact(contact);

	}

	public ContactHelper modifyContact(String idFromDB,int index, ContactData contact) {
		manager.navigateTo().mainPage();
		selectContact(idFromDB);
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		manager.navigateTo().mainPage();
		 //update model
	    manager.getModel().remove(index).addContact(contact);
		return this;
	}

	public void modifyContactGroup(String idFromDB) {
		manager.navigateTo().mainPage();
		randomGroupSelection(MODIFICATION);
		checkContact(idFromDB);
		submitGroupChange();
		manager.navigateTo().goToSubmittedGroupPage();
		resetGroupsListToAllValue();

	}

	public void deleteContact(String idFromDB,int index) {
		selectContact(idFromDB);
		deleteContact();
		manager.navigateTo().mainPage();
		 //update model
	    manager.getModel().remove(index);
		
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public void initContactCreation() {
		if (!manager.navigationHelper.onCreateContactPage()) {
			driver.findElement(By.linkText("add new")).click();

		}

	}

	public void fillContactForm(ContactData contact, boolean formType) {
		if (formType == CREATION) {
			selectByText(By.name("new_group"), contact.getGroup_name());
		}
		type(By.name("firstname"), contact.getFirst_name());
		type(By.name("lastname"), contact.getLast_name());
		type(By.name("address"), contact.getAddress_1());
		type(By.name("home"), contact.getHome_tel());
		type(By.name("mobile"), contact.getMobile_tel());
		type(By.name("work"), contact.getWork_tel());
		type(By.name("email"), contact.getEmail());
		type(By.name("email2"), contact.getEmail2());
		type(By.name("byear"), contact.getBirth_year());
		selectByText(By.name("bmonth"), contact.getBirth_month());
		selectByText(By.name("bday"), contact.getBirth_day());
		type(By.name("address2"), contact.getAddress_2());
		type(By.name("phone2"), contact.getPhone_2());

	}

	public void submitContactCreation() {
		driver.findElement(By.name("submit")).click();

	}

	public void selectContact(String idFromDB) {
		click(By.xpath("//a[@href='edit.php?id=" + idFromDB + "']"));
	}
	public void checkContact(String idFromDB) {
		if (manager.navigationHelper.onMainPage() == true) {
			click(By.xpath("//td/input[@value='" + idFromDB + "']"));
		} else {
			manager.navigateTo().mainPage();
			click(By.xpath("//td/input[@value='" + idFromDB + "']"));
					
		}
	}

	public void deleteContact() {
		click(By.xpath("//input[@value='Delete']"));

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

	public String randomGroupSelection(boolean formType) {
		List<GroupData> groupNameList = manager.getHibernateHelper().listGroups();
		Random rnd = new Random();
		int index2 = rnd.nextInt(groupNameList.size() - 1);
		if (formType == MODIFICATION) {
			selectGroupForChange(index2);
		} else {
			selectGroupForContactCreation(index2);
		}
		String groupName;
		groupName = groupNameList.get(index2).getName();
		return groupName;
	}

	public void selectGroupForContactCreation(int index2) {
		click(By.xpath("(//select[@name='new_group']/option)[" + (index2 + 1) + "]"));

	}

	public void selectGroupForChange(int index2) {
		click(By.xpath("(//select[@name='to_group']/option)[" + (index2 + 1) + "]"));
	}

	public WebElement selectId(int index) {
		WebElement id = driver.findElement(By.xpath("(//input[@id])[index+1]"));
		return id;
	}

	public void resetGroupsListToAllValue() {
		click(By.xpath("//select[@onchange='this.parentNode.submit()']/option[@value=''] "));

	}
	public String getHomeTel(String text) {
		Pattern regex = Pattern.compile("H:\\s*\\w*[\\w*\\-*]+w*\\\n");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		
		}
}
}