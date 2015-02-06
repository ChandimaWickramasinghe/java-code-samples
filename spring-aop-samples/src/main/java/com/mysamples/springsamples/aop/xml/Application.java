package com.mysamples.springsamples.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
	      ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");

	       Student student = (Student) context.getBean("student");

	       student.getName();
	       student.getAge();
	       
	       try {
	    	   student.printThrowException();
	       } catch(IllegalArgumentException e) {
	    	   System.out.println("Exception is - " + e);
	       }
	       
	       Teacher teacher = (Teacher) context.getBean("teacher");
	       
	       teacher.setName("Chandima");
	       teacher.setCourse("Maths");
	       
	       teacher.getName();
	       
	}

}
