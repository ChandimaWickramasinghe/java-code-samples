package com.mysamples.springsamples.annotation.one;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main Application class
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 */

@Configuration
@ComponentScan
public class Application {

	// @Bean
	// MessageService setHelloWorld() {
	// return new MessageService() {
	// public String getMessage() {
	// return "Hello World!";
	// }
	// };
	// }

	@Bean
	MessageService setEndWorld() {
		return new MessageService() {
			public String getMessage() {
				return "End World!";
			}
		};
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Application.class);
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}

}
