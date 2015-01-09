package com.mysamples.springsamples.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HelloWorldProcessorTwo implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out
				.println("HelloWorldProcessorTwo - postProcessAfterInitialization - "
						+ arg1);
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out
				.println("HelloWorldProcessorTwo - postProcessBeforeInitialization - "
						+ arg1);
		return arg0;
	}

}
