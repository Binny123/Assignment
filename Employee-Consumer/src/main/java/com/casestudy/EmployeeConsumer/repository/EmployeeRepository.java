package com.casestudy.EmployeeConsumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.EmployeeConsumer.model.Employee;

@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, Integer> {

}