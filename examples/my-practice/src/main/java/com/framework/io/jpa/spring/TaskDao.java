package com.framework.io.jpa.spring;

import com.framework.io.hibernate.TaskAnnotation;

public interface TaskDao {
	public void save(TaskAnnotation task);
	public void update(int id);
	public TaskAnnotation search(String title);
	public void delete(int id);

}
