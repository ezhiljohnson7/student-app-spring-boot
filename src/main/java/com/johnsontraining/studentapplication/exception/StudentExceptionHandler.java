package com.johnsontraining.studentapplication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ StudentNotFoundException.class, DuplicateStudentException.class })
	public ResponseEntity<?> handleStudentExceptions(Exception ex, WebRequest request) {

		Map<String, String> map = new HashMap<>();
		map.put("status", ex.getMessage());

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

}
