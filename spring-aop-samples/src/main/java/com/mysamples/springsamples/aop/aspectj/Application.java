package com.mysamples.springsamples.aop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("AspectJBeans.xml");
		Student student = (Student) context.getBean("student");
		student.setName("Chandima");
		student.getName();

	}

}
