package com.mysamples.springsamples.codewithinterface;

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

		TextEditor textEditor = (TextEditor) context.getBean("textEditor");

		textEditor.setText();

	}

}
