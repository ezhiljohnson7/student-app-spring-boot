package com.johnsontraining.studentapplication.exception;

public class DuplicateStudentException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateStudentException() {
		 super();
	}
	
	public DuplicateStudentException(String message) {
		 super(message);
	}
	
	public DuplicateStudentException(Throwable cause) {
		 super(cause);
	}
	
	public DuplicateStudentException(String message, Throwable cause) {
		super(message, cause);
	}
}
