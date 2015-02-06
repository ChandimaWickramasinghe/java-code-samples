package com.mysamples.springsamples.aop.xml;

public class Student {
	
	 private Integer age;
	   private String name;

	   public void setAge(Integer age) {
	      this.age = age;
	   }
	   public Integer getAge() {
	      return age;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getName() {
	      return name;
	   }
	   
	   public void printThrowException(){
	       throw new IllegalArgumentException();
	   }

}
