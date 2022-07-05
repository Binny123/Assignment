package com.casestudy.EmployeePublisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.EmployeeDto;

@RestController
public class EmployeePublisherController {

	@Autowired
	private KafkaTemplate<String, EmployeeDto> kafkaTemplate;

	@PostMapping("/createEmployee")
	public String addEmployee(@RequestBody EmployeeDto e) {
		kafkaTemplate.send("employeeManagement", e);
		return "Data Published";

	}
}