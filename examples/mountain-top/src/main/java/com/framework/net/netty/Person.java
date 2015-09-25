package com.framework.net.netty;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -8020811503661142095L;

	private int id;
	private String name;
	private int age;
	private String description;

	public Person() {
		id = (int) Math.random();
		init();
	}

	public Person(int id) {
		this.id = id;
		init();
	}

	private void init() {
		name = "bengbeng" + id;
		age = 3;
		description = "Good Boby";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
