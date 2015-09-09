package com.framework.io.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HbmCRUDDaoAnnotation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaskAnnotation readBookAnno = new TaskAnnotation();
		readBookAnno.setTitle("read book");
		readBookAnno.setDescription("java book annotation");
		readBookAnno.setUserId(4);
		
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(readBookAnno);
		tx.commit();
		session.close();

		deleteByTitle("read book");
		save(readBookAnno);
		TaskAnnotation result = searchByTitle("read book");
		if (result != null) {
			System.out.print(result.getId() + ":" + result.getTitle() + ":"
					+ result.getDescription() + ":" + result.getUserId());
		}
		System.exit(0);

	}

	private static void save(TaskAnnotation readBook) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(readBook);
		tx.commit();
		session.close();
	}

	private static TaskAnnotation searchByTitle(String title) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from TaskAnnotation as t where t.title='"
				+ title + "'");
		TaskAnnotation task = null;
		if (query.list().size() > 0) {
			task = (TaskAnnotation) query.list().get(0);
		}
		tx.commit();
		session.close();

		return task == null ? null : task;
	}

	private static void deleteByTitle(String title) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("delete from TaskAnnotation as t where t.title='"+title+"'");
        query.executeUpdate();
		
		tx.commit();
		session.close();
	}

}
