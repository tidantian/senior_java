package com.framework.io.jpa.spring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.io.hibernate.TaskAnnotation;

@Component
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	TaskDaoImpl() {
		System.out.println("here...........");
	}

	@Override
	public void save(TaskAnnotation task) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			entityManager.persist(task);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void update(int id) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			TaskAnnotation task = entityManager.find(TaskAnnotation.class, id);
			task.setDescription("after update");
			entityManager.persist(task);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}

	}

	@Override
	public TaskAnnotation search(String title) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			Object task = entityManager.createQuery("from TaskAnnotation as t where t.title='"+title+"'").getSingleResult();
			if (task != null) {
				return (TaskAnnotation)task;
			}
		} finally {
			System.out.print("In Search.");
			entityManager.close();
		}
		
		return null;
	}

	@Override
	public void delete(int id) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			TaskAnnotation task = entityManager.find(TaskAnnotation.class, id);
			entityManager.remove(task);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}

	}

}
