package com.mysamples.springsamples.postprocessors;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Application class
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 */
public class Application {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");

		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

		obj.getMessage1();

		HelloWorld singletonA = (HelloWorld) context.getBean("singletonCheck");
		singletonA.setMessage1("I'm Singleton A");
		singletonA.getMessage1();

		HelloWorld singletonB = (HelloWorld) context.getBean("singletonCheck");
		singletonB.getMessage1();

		HelloWorld prototypeA = (HelloWorld) context.getBean("prototypeCheck");
		prototypeA.setMessage1("I'm Prototype A");
		prototypeA.getMessage1();

		HelloWorld prototypeB = (HelloWorld) context.getBean("prototypeCheck");
		prototypeB.getMessage1();

		context.registerShutdownHook();
	}

}
