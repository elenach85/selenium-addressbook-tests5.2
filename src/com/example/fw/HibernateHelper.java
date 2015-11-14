package com.example.fw;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.tests.ContactData;
import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	public List<GroupData> listGroups() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new SortedListOf<GroupData>(
              (List<GroupData>) session.createQuery("from GroupData").list());
		} finally {
          trans.commit();
		}
	}
	
	public List<ContactData> listContacts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new SortedListOf<ContactData>(
              (List<ContactData>) session.createQuery("from ContactData").list());
		} finally {
          trans.commit();
		}
		
	}
	
	public ContactData getContact(String id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
			return (ContactData) session.createQuery("from ContactData where id='"+id + "'").uniqueResult();
		} finally {
			trans.commit();
		}
		
	}

	public GroupData getGroup(String groupId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
			return (GroupData) session.createQuery("from GroupData where group_id='"+groupId + "'").uniqueResult();	
		} finally {
			trans.commit();
		}
	}
}
