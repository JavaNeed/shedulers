package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
