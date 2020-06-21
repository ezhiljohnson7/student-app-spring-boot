package com.johnsontraining.studentapplication.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.johnsontraining.studentapplication.model.Student;

@RestController
@RequestMapping("${api.version}")
public class StudentController {

	private Map<String, Student> cache = new HashMap<String, Student>();

	@GetMapping("/student/{studentName}")
	public ResponseEntity<?> getStudentDetails(@PathVariable String studentName) {

		if (cache.containsKey(studentName)) {
			Student student = cache.get(studentName);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		} else {
			throw new StudentNotFoundException(studentName + " is not available in the database.");
		}
	}

	@GetMapping("/students")
	public ResponseEntity<?> getAllStudentDetails() {

		if (!cache.isEmpty()) {
			return new ResponseEntity<Map<String, Student>>(cache, HttpStatus.OK);
		} else {
			throw new StudentNotFoundException("Students database is empty.");
		}
	}

	@PostMapping("/student")
	public ResponseEntity<?> saveStudent(@RequestBody(required = true) Student student) {

		if (!cache.containsKey(student.getName())) {
			cache.put(String.valueOf(student.getName()), student);
			return new ResponseEntity<String>(
					String.format("Successfully added the student with name %s to the database.", student.getName()),
					HttpStatus.OK);
		} else {
			throw new DuplicateStudentException(student.getName() + " is already available in the database.");
		}
	}

	@PutMapping("/student")
	public ResponseEntity<?> updateStudent(@RequestBody(required = true) Student student) {

		if (cache.containsKey(student.getName())) {
			cache.put(String.valueOf(student.getName()), student);
			return new ResponseEntity<String>(
					String.format("Successfully updated the student details with name %s in the database.",
							student.getName()),
					HttpStatus.OK);
		} else {
			throw new StudentNotFoundException(student.getName() + " is not available in the database.");
		}
	}

	@DeleteMapping("/student/{studentName}")
	public ResponseEntity<?> deleteStudent(@PathVariable String studentName) {

		if (cache.containsKey(studentName)) {
			cache.remove(studentName);
			return new ResponseEntity<String>(String
					.format("Successfully deleted the student details with name %s in the database.", studentName),
					HttpStatus.OK);
		} else {
			throw new StudentNotFoundException(studentName + " is not available in the database.");
		}
	}
}
