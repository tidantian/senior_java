package com.framework.spring.aop;

import org.springframework.stereotype.Component;

@Component("meeting")
public class Meeting {
	private String reporter;
	private String topic;

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	Meeting() {
		this.topic = "Java";
		this.reporter = "Tidan";
	}
	
	Meeting(String topic, String reporter) {
		this.topic = topic;
		this.reporter = reporter;
	}

	public void start() {
		System.out.println("Meeting Topic: " + topic);
		System.out.println("Reporter: " + reporter);
		System.out.println("Meeting show... ");
	}

	public void firstPart() {
		System.out.println("First Part... ");
	}

	public void rest() {
		System.out.println("rest... ");
	}

	public void secordPart() {
		System.out.println("Secord Part. ");
	}
}
