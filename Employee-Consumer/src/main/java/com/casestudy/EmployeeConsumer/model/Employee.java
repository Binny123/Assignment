package com.casestudy.EmployeeConsumer.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table // represents that it will map to ‘invoice’ table in Cassandra DB.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {

	public static final Long variable = 1L;

	@PrimaryKey
	private int empId;
	private String firstName;
	private String lastName;
	private String department;
	private String address;
	private int age;
}
