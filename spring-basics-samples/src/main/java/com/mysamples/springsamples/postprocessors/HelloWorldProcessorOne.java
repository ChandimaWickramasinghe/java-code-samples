package com.mysamples.springsamples.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HelloWorldProcessorOne implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out
				.println("HelloWorldProcessorOne - postProcessAfterInitialization - "
						+ arg1);
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out
				.println("HelloWorldProcessorOne - postProcessBeforeInitialization - "
						+ arg1);
		return arg0;
	}

}
