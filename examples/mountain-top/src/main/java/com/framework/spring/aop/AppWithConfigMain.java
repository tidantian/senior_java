package com.framework.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppWithConfigMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/resources/aop_beans.xml");
		Meeting meeting = ctx.getBean(Meeting.class);
		meeting.start();
		meeting.firstPart();
		meeting.rest();
		meeting.secordPart();
	}

}
