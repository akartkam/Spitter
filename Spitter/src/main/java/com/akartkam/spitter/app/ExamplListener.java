package com.akartkam.spitter.app;

import org.springframework.context.ApplicationListener;

public class ExamplListener implements ApplicationListener<ExamplEvent> {

	@Override
	public void onApplicationEvent(ExamplEvent event) {
		System.out.println(event.getExampl().getName());
		
	}

}
