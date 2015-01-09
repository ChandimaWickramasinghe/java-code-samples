package com.mysamples.springsamples.annotation.two;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		TextEditor textEditor = (TextEditor) context.getBean(TextEditor.class);

		textEditor.setText();

	}

}
