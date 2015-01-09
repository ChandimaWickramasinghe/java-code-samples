package com.mysamples.springsamples.customeventhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public MyEventPublisher myEventPublisher() {
		return new MyEventPublisher();
	}

	@Bean
	public MyEventHandler myEventHandler() {
		return new MyEventHandler();
	}

}
