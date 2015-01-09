package com.mysamples.springsamples.eventhandlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class ContextStopEventHandler implements
		ApplicationListener<ContextClosedEvent> {

	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println("Context stopped");

	}

}
