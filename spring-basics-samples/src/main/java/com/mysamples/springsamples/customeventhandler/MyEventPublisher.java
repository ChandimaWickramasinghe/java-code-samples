package com.mysamples.springsamples.customeventhandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class MyEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;

	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;

	}

	public void publish() {
		publisher.publishEvent(new MyEvent(this));
	}

}
