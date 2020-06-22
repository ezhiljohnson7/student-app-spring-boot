package com.johnsontraining.studentapplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnsontraining.studentapplication.exception.DuplicateStudentException;
import com.johnsontraining.studentapplication.exception.StudentNotFoundException;
import com.johnsontraining.studentapplication.model.ResponseDto;
import com.johnsontraining.studentapplication.model.Student;
import com.johnsontraining.studentapplication.model.Students;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("api/v1")
@Api(value = "Student")
public class StudentController {
	
	private Students cache = new Students();

	@GetMapping("/student/{studentName}")
	public ResponseEntity<?> getStudentDetails(@PathVariable String studentName) {

		if(cache.getStudents() == null) {
			throw new StudentNotFoundException("Students database is empty.");
		} else {
			for (Student student : cache.getStudents()) {
				if (student.getName().equalsIgnoreCase(studentName)) {
					return new ResponseEntity<Student>(student, HttpStatus.OK);
				}
			}
		}
		throw new StudentNotFoundException("Student is not available in the database");
	}

	@GetMapping("/students")
	public ResponseEntity<?> getAllStudentDetails() {

		if(cache.getStudents() == null) {
			throw new StudentNotFoundException("Students database is empty.");
		} else {
			return new ResponseEntity<Students>(cache, HttpStatus.OK);
		}
	}

	@PostMapping("/student")
	public ResponseEntity<?> saveStudent(@RequestBody(required = true) Student student) {
		
		boolean firstStudent = false;
		if(cache.getStudents() == null) {
			
			List<Student> students = new ArrayList<Student>();
			students.add(student);
			cache.setStudents(students);
			firstStudent = true;
		} else {
			for(Student currStudent : cache.getStudents()) {
				if(student.getName().equalsIgnoreCase(currStudent.getName())) {
					throw new DuplicateStudentException(student.getName() + " is already available in the database.");
				}
			}
		}
		if(!firstStudent) {
			cache.getStudents().add(student);
		}
		
		ResponseDto response = new ResponseDto();
		response.setStatus("pass");
		response.setMessage(String.format("Successfully added the student with name '%s' to the database.", student.getName()));
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	@PutMapping("/student")
	public ResponseEntity<?> updateStudent(@RequestBody(required = true) Student student) {
		if(cache.getStudents() == null) {
			throw new StudentNotFoundException("Students database is empty.");
		} else {
			for (Student currStudent : cache.getStudents()) {
				if (student.getName().equalsIgnoreCase(currStudent.getName())) {
					cache.getStudents().remove(currStudent);
					cache.getStudents().add(student);
					
					ResponseDto response = new ResponseDto();
					response.setStatus("pass");
					response.setMessage(String
							.format("Successfully updated the student with name '%s' in the database.", student.getName()));
					return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
				}
			}
		}
		throw new StudentNotFoundException(String.format("Student with name '%s' not found in the database.", student.getName()));
	}

	@DeleteMapping("/student/{studentName}")
	public ResponseEntity<?> deleteStudent(@PathVariable String studentName) {
		
		if(cache.getStudents() == null) {
			throw new StudentNotFoundException("Students database is empty.");
		} else {
			for (Student currStudent : cache.getStudents()) {
				if (studentName.equalsIgnoreCase(currStudent.getName())) {
					cache.getStudents().remove(currStudent);
					ResponseDto response = new ResponseDto();
					response.setStatus("pass");
					response.setMessage(String.format("Successfully delete the user %s from the database.", studentName));
					return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
				}
			}
		}
		throw new StudentNotFoundException(String.format("Student with name '%s' not found in the database.", studentName));
	}
	
	@DeleteMapping("/students")
	public ResponseEntity<?> deleteAllStudents() {
		if(cache.getStudents() == null) {
			throw new StudentNotFoundException("Students database is empty.");
		} else {
			cache.getStudents().clear();
			ResponseDto response = new ResponseDto();
			response.setStatus("pass");
			response.setMessage("Successfully deleted all the users from database.");
			return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
		} 
	}
}
