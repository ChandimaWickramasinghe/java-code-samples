package com.mysamples.springsamples.eventhandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public HelloWorld helloWorld() {
		HelloWorld obj = new HelloWorld();
		obj.setMessage("Hello World!");
		return obj;
	}

	@Bean
	public ContextStartEventHandler contextStartEventHandler() {
		return new ContextStartEventHandler();
	}

	@Bean
	public ContextStopEventHandler contextStopEventHandler() {
		return new ContextStopEventHandler();
	}

}
