package com.johnsontraining.studentapplication.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class Students {

	private List<Student> students;
}
