package com.framework.io.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HbmCRUDDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Task readBook = new Task();
		readBook.setTitle("read book");
		readBook.setDescription("java book");
		readBook.setUserId(4);
		
		TaskAnnotation readBookAnno = new TaskAnnotation();
		readBookAnno.setTitle("read book");
		readBookAnno.setDescription("java book annotation");
		readBookAnno.setUserId(4);

		deleteByTitle("read book");
		save(readBook);
		Task result = searchByTitle("read book");
		if (result != null) {
			System.out.print(result.getId() + ":" + result.getTitle() + ":"
					+ result.getDescription() + ":" + result.getUserId());
		}
		System.exit(0);

	}

	private static void save(Task readBook) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(readBook);
		tx.commit();
		session.close();
	}

	private static Task searchByTitle(String title) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Task as t where t.title='"
				+ title + "'");
		Task task = null;
		if (query.list().size() > 0) {
			task = (Task) query.list().get(0);
		}
		tx.commit();
		session.close();

		return task == null ? null : task;
	}

	private static void deleteByTitle(String title) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("delete from Task as t where t.title='"+title+"'");
        query.executeUpdate();
		
		tx.commit();
		session.close();
	}

}
