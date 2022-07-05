package com.casestudy.EmployeeConsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.casestudy.EmployeeConsumer.controller.service.EmpService;
import com.casestudy.model.EmployeeDto;

@RestController
@RequestMapping(value = "/v1")
public class EmployeeController {

	@Autowired
	private EmpService empService;

	@GetMapping("/getEmp/{empId}")
	public EmployeeDto getEmployee(@PathVariable int empId) {
		return empService.getEmployee(empId);
	}

	@PostMapping("/save")
	public String saveEmp(@RequestBody EmployeeDto employeeDto) {
		return empService.createEmployee(employeeDto);
	}

	@GetMapping("/getAllEmps")
	public List<EmployeeDto> getAllEmployees() {
		return empService.getAllEmps();
	}

	@DeleteMapping("/deleteEmp/{empId}")
	public String deleteEmployee(@PathVariable int empId) {
		return empService.deleteEmp(empId);
	}
	
	@PutMapping("/updateEmp")
	public ResponseEntity<String> updateCustomer(@RequestBody EmployeeDto employeeDto) {
		
		try {
			return new ResponseEntity<String>(empService.updateEmp(employeeDto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
