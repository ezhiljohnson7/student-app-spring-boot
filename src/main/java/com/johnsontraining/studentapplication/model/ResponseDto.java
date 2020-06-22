package com.johnsontraining.studentapplication.model;


import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ResponseDto {
	
	private String status;
	private String message;

}
