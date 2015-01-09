package com.mysamples.springsamples.customeventhandler;

import org.springframework.context.ApplicationListener;

public class MyEventHandler implements ApplicationListener<MyEvent> {

	public void onApplicationEvent(MyEvent event) {
		System.out.println(event.fireEvent());

	}

}
