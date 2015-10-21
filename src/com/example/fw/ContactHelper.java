package com.example.fw;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.tests.GroupData;
import com.example.utils.SortedListOf;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

public class ContactHelper extends WebDriverHelperBase {
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	private SortedListOf<ContactData> cachedContacts;

	public SortedListOf<ContactData> getContactsFromUI() {
		if (cachedContacts == null) {
			rebuildCacheFromUI();
		}
		return cachedContacts;
	}

	private void rebuildCacheFromUI() {
		cachedContacts = new SortedListOf<ContactData>();
		List<WebElement> rows = findElements();
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			ContactData contact = new ContactData();
			String last_name_title = columns.get(1).getText();
			String first_name_title = columns.get(2).getText();
			String email = columns.get(3).getText();
			String home_tel = columns.get(4).getText();
			String id = columns.get(0).findElement(By.xpath(".//input[@value]")).getAttribute("value");
			cachedContacts.add(new ContactData().withFirstname(first_name_title).withLastname(last_name_title)
					.withEmail(email).withHomeTel(home_tel).withId(id));

		}
	}

	public SortedListOf<ContactData> getContactsFromDB() {
		if (cachedContacts == null) {
			rebuildCacheFromDB();
		}
		return cachedContacts;
	}

	private void rebuildCacheFromDB() {
		cachedContacts = new SortedListOf<ContactData>(manager.getHibernateHelper().listContacts());

	}

	public void createContact(ContactData contact) {
		initContactCreation();
		randomGroupSelection(CREATION);
		fillContactForm(contact, CREATION);
		submitContactCreation();
		manager.navigateTo().mainPage();
		rebuildCacheFromDB();

	}

	public ContactHelper modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		selectContact(index);
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		manager.navigateTo().mainPage();
		rebuildCacheFromDB();
		return this;
	}

	public void modifyContactGroup(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		randomGroupSelection(MODIFICATION);
		checkContact(index);
		submitGroupChange();
		manager.navigateTo().goToSubmittedGroupPage();
		resetGroupsListToAllValue();

	}

	public void deleteContact(int index) {
		selectContact(index);
		deleteContact();
		manager.navigateTo().mainPage();
		rebuildCacheFromDB();

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

	public void selectContact(int index) {
		click(By.xpath("//tr[@name='entry'][" + (index + 1) + "]/td/a/img[@title='Edit']"));

	}

	public void deleteContact() {
		click(By.xpath("//input[@value='Delete']"));

	}

	public void checkContact(int index) {
		if (manager.navigationHelper.onMainPage() == true) {
			click(By.xpath("(//input[@type='checkbox'])[" + (index + 1) + "]"));
		} else {
			manager.navigateTo().mainPage();
			click(By.xpath("(//input[@type='checkbox'])[" + (index + 1) + "]"));
		}
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

}
