package com.mysamples.springsamples.collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Application class
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 */
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");

		CollectionTest colTest = (CollectionTest) context
				.getBean("collectionTest");

		colTest.getAddressList();
		colTest.getAddressMap();
		colTest.getAddressProperties();
		colTest.getAddressSet();
	}

}
