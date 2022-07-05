
package com.casestudy.EmployeeConsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.casestudy.EmployeeConsumer.controller.service.EmpService;
import com.casestudy.model.EmployeeDto;

@Component
public class EmployeeConsumer {
	
	@Autowired
	private EmpService empService;
	
	
	@KafkaListener(topics = "employeeManagement", groupId = "employeeGroup", containerFactory = "employeeKafkaListenerContainerFactory")
	public void consume(EmployeeDto employeeDto) {
		System.out.println(employeeDto.toString());	
		empService.createEmployee(employeeDto);
	}
}
