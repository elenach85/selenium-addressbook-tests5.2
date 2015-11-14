package com.example.tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import  org.testng.annotations.Test;
import static com.example.tests.TestBase.wrapGroupsForDataProvider;
import static com.example.tests.GroupDataGenerator.generateRandomGroups;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matcher.*;
import com.example.utils.ListOf;
import com.example.utils.SortedListOf;
import com.google.common.collect.Lists;
public class GroupCreationTests extends TestBase {


private List<GroupData> list;

@Test(dataProvider="groupsFromCsvFile")
  public void testGroupCreationWithValidData(GroupData group) throws Exception {

    // save old state
	SortedListOf<GroupData> oldList = app.getModel().getGroups();
       //actions
   app.getGroupHelper().createGroup(group);
    //save new state
    SortedListOf<GroupData> newList=app.getModel().getGroups();
    //compare state
assertThat(newList,equalTo(oldList.withAdded(group)));
if (wantToCheck()) {
	if ("yes".equals(app.getProperty("check.db"))) {
		assertThat(app.getModel().getGroups(),equalTo(app.getHibernateHelper().listGroups()));	
	}
	if ("yes".equals(app.getProperty("check.ui"))) {
	assertThat(app.getModel().getGroups(),equalTo(app.getGroupHelper().getGroupsFromUI()));
	  }	
}

}
}
