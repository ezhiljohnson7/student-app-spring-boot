package com.johnsontraining.studentapplication.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Component
public class Student {

	private @NonNull String name;
	private int age;
	private @NonNull String emailId;
	private long mobileNo;
	private int semester;
	private @NonNull String department;
	private List<String> hobbies;
}
