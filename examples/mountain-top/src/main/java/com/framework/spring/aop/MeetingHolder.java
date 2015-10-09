package com.framework.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("holder")
@Aspect
public class MeetingHolder {
	private String name = "holder";
	private int age;

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

	@Before("execution(* com.framework.spring.aop.Meeting.start(..))")
	public void sayHi() {
		System.out.println(name + " say Hello to all.");
	}

	@After("execution(public void com.framework.spring.aop.Meeting.rest(..))")
	public void sayThanks() {
		System.out.println(name + " say Thanks to all");
	}

	@After("execution(public void com.framework.spring.aop.Meeting.secordPart())")
	public void sayGoodbye() {
		System.out.println(name + " say Goodbye to all");
	}

}
