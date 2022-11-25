package com.example.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	Employee findByName(String name);

	Optional<Employee> findById(int l);

	
	

}
