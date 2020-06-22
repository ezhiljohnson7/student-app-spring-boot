package com.johnsontraining.studentapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.johnsontraining.studentapplication.model.ResponseDto;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ StudentNotFoundException.class, DuplicateStudentException.class })
	public ResponseEntity<?> handleStudentExceptions(Exception ex, WebRequest request) {

		ResponseDto response = new ResponseDto();
		response.setStatus("fail");
		response.setMessage(ex.getMessage());

		return new ResponseEntity<ResponseDto>(response, HttpStatus.NOT_FOUND);
	}

}
