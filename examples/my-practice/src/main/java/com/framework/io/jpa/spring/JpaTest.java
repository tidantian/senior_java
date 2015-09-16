package com.framework.io.jpa.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.io.hibernate.TaskAnnotation;

@Component
public class JpaTest {
	@Autowired
	private TaskDao taskDao;

	/**
	 * @param args
	 */
	public String test() {
		StringBuffer traceLog = new StringBuffer();
		TaskAnnotation task = new TaskAnnotation();
		task.setTitle("title_jpa");
		task.setDescription("for jpa test");
		task.setUserId(168);

		taskDao.save(task);
		traceLog.append("Entity Save\n");

		TaskAnnotation taskSearch = taskDao.search("title_jpa");
		traceLog.append("Entity Search");
		traceLog.append(taskSearch.getId());
		traceLog.append(taskSearch.getTitle());
		traceLog.append(taskSearch.getUserId());
		traceLog.append(taskSearch.getDescription());

		traceLog.append("Entity Update");
		taskDao.update(taskSearch.getId());

		traceLog.append("Entity Delete");
		taskDao.delete(taskSearch.getId());
		try {
			taskDao.search("title_jpa");
		} catch (RuntimeException e) {
			traceLog.append("Entity Delete Success");
		}
		
		return traceLog.toString();
	}

}
