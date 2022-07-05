package com.casestudy.EmployeeConsumer.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.casestudy.EmployeeConsumer.model.Employee;
import com.casestudy.EmployeeConsumer.repository.EmployeeRepository;
import com.casestudy.model.EmployeeDto;

@Component
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	@Cacheable(cacheNames = "employee-cache",key = "#p0", condition = "#p0!=null")
	public EmployeeDto getEmployee(int empId) {
		System.out.println("First Db Hit in GetEmployee");
		Optional<Employee> emp = empRepo.findById(empId);
		if (emp.isPresent()) {
			return EmployeeDto.builder().empId(emp.get().getEmpId()).firstName(emp.get().getFirstName())
					.lastName(emp.get().getLastName()).age(emp.get().getAge()).address(emp.get().getAddress())
					.department(emp.get().getDepartment()).build();
		} else {
			return null;
		}
	}

	@Override
	public String createEmployee(EmployeeDto employeeDto) {
		new Employee();
		Employee e1 = Employee.builder().empId(employeeDto.getEmpId()).firstName(employeeDto.getFirstName())
				.lastName(employeeDto.getLastName()).age(employeeDto.getAge()).address(employeeDto.getAddress())
				.department(employeeDto.getDepartment()).build();

		empRepo.save(e1);
		return "Employee created succesfully";
	}

	@Override
	@Cacheable(cacheNames = "employee-cache")
	public List<EmployeeDto> getAllEmps() {
		System.out.println("First Db Hit in GetAllEmps");
		List<EmployeeDto> empDtoList = new ArrayList<>();

		try {

			List<Employee> empList = empRepo.findAll();
			if (!empList.isEmpty()) {
				for (Employee emp : empList) {
					EmployeeDto employeeDto = EmployeeDto.builder().empId(emp.getEmpId()).firstName(emp.getFirstName())
							.lastName(emp.getLastName()).age(emp.getAge()).address(emp.getAddress())
							.department(emp.getDepartment()).build();
					empDtoList.add(employeeDto);

				}
			}
		} catch (Exception e) {
			System.out.println("Exception in GetAllEmployees");

		}
		return empDtoList;
	}

	@Override
	@CacheEvict(value = "employee-cache", key = "#p0", condition = "#p0!=null", allEntries = true)
	public String deleteEmp(int empId) {
		System.out.println("First Db Hit in DeleteEmployee");
		Optional<Employee> emp = empRepo.findById(empId);
		if (emp.isPresent()) {
			empRepo.delete(emp.get());
			return "Employee Deleted Successfully";
		} else {
			return "Employee Not Found!!!!!!!!!!!!!";
		}
	}

	@Override
	@CachePut(value = "employee-cache", key = "#p0")
	public String updateEmp(EmployeeDto employeeDto) {
		Optional<Employee> emp = empRepo.findById(employeeDto.getEmpId());
		Employee updatedEmp = null;
		if (emp.isPresent()) {
			updatedEmp = Employee.builder().empId(employeeDto.getEmpId()).firstName(employeeDto.getFirstName())
					.lastName(employeeDto.getLastName()).age(employeeDto.getAge()).address(employeeDto.getAddress())
					.department(employeeDto.getDepartment()).build();
		} else {
			updatedEmp = Employee.builder().empId(employeeDto.getEmpId()).firstName(employeeDto.getFirstName())
					.lastName(employeeDto.getLastName()).age(employeeDto.getAge()).address(employeeDto.getAddress())
					.department(employeeDto.getDepartment()).build();

		}
		empRepo.save(updatedEmp);
		return "Employee has been Updated";

	}

}
