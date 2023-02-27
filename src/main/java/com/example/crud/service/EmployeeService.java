package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.crud.EmployeeNotFoundException;
import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	public List<Employee> saveEmployees(List<Employee> employees) {
		return repository.saveAll(employees);
	}
	
	public List<Employee> getEmployees(){
		return repository.findAll();
	}
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException{
		Optional<Employee> result=repository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new EmployeeNotFoundException("Employee not found");
		}
		//return repository.findById(id);
	}
	public Employee getEmployeeByName(String name) {
		
		return repository.findByName(name);
	}
	/*public String deleteEmployee(int id) {
		repository.deleteById(id);
		return " Employee removed";
	}*/
	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee deleted successfully"));
		
		repository.delete(employee);
	}

	public Employee updateEmployee(Employee employee) {
		Optional<Employee> existingEmployee=repository.findById(employee.getId());
		Employee newEntity = null;
		if(existingEmployee.isPresent()) {
		newEntity=existingEmployee.get();
		newEntity.setName(employee.getName());
		newEntity.setSalary(employee.getSalary());
		}
		return repository.save(newEntity);
	}
}
