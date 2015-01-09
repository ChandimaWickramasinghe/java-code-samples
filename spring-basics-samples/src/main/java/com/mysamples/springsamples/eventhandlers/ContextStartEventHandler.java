package com.mysamples.springsamples.eventhandlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class ContextStartEventHandler implements
		ApplicationListener<ContextStartedEvent> {

	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("Context started");
	}

}
