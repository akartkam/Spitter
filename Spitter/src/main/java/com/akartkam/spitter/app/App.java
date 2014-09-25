package com.akartkam.spitter.app;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static final void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:app-context.xml");
		Exampl exampl = new Exampl("Exampl");
		App app = new App();
		context.publishEvent( new ExamplEvent(app, exampl));
	}
	
}
