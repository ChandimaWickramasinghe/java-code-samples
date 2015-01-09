package com.mysamples.springsamples.postprocessors;

public class HelloWorld {
	private String message1;

	private String message2;

	public void setMessage2(String message) {
		this.message2 = message;
	}

	public void getMessage2() {
		System.out.println("Your Message : " + message2);
	}

	public void setMessage1(String message) {
		this.message1 = message;
	}

	public void getMessage1() {
		System.out.println("Your Message 1 : " + message1);
	}

	public void init() {
		System.out.println("Initializing - " + message1 + "," + message2);
	}

	public void destroy() {
		System.out.println("Destroying - " + message1 + "," + message2);
	}
}
