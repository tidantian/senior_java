package com.framework.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect
public class MeetingLogger {
	@Before("execution(* com.framework.spring.aop.Meeting.*(..))")
	void log(JoinPoint jp) {
		System.out.println("Call function: " + jp.getSignature().getName());
		System.out.print("Args: ");
		System.out.println(Arrays.asList(jp.getArgs()));
	}
}
