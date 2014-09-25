package com.akartkam.spitter.app;

import org.springframework.context.ApplicationEvent;

public class ExamplEvent extends ApplicationEvent {
	private Exampl exampl;

	public ExamplEvent(Object source, Exampl exampl) {
		super(source);
		this.exampl = exampl;
	}

	public Exampl getExampl() {
		return exampl;
	}
	
	
	
}
