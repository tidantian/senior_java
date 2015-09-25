package com.framework.io.jpa;

import com.framework.io.hibernate.TaskAnnotation;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaskAnnotation task = new TaskAnnotation();
		task.setTitle("title_jpa");
		task.setDescription("for jpa test");
		task.setUserId(168);

		TaskDao taskDao = new TaskDaoImpl();
		taskDao.save(task);
		System.out.println("Entity Save");

		TaskAnnotation taskSearch = taskDao.search("title_jpa");
		System.out.println("Entity Search");
		System.out.println(taskSearch.getId());
		System.out.println(taskSearch.getTitle());
		System.out.println(taskSearch.getUserId());
		System.out.println(taskSearch.getDescription());

		System.out.println("Entity Update");
		taskDao.update(taskSearch.getId());

		System.out.println("Entity Delete");
		taskDao.delete(taskSearch.getId());
		try {
			taskDao.search("title_jpa");
		} catch (RuntimeException e) {
			System.out.println("Entity Delete Success");
		}

		System.exit(0);
	}

}
