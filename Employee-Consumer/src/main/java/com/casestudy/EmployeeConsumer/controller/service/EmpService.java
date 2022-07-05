package com.casestudy.EmployeeConsumer.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.casestudy.model.EmployeeDto;

@Service
public interface EmpService {
	EmployeeDto getEmployee(int empId);

	String createEmployee(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmps();

	String deleteEmp(int empId);

	String updateEmp(EmployeeDto employeeDto);
}
