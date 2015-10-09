package com.framework.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppWithAnotationMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/resources/META-INF/beans_aop.xml");
		Meeting meeting = ctx.getBean(Meeting.class);
		meeting.start();
		meeting.firstPart();
		meeting.rest();
		meeting.secordPart();
	}

}
