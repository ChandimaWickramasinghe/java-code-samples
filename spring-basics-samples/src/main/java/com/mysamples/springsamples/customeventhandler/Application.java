package com.mysamples.springsamples.customeventhandler;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main Application class
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 */
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		MyEventPublisher publisher = (MyEventPublisher) context
				.getBean(MyEventPublisher.class);
		publisher.publish();

	}

}
