package com.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

	private int empId;
	private String firstName;
	private String lastName;
	private String department;
	private String address;
	private int age;
}
