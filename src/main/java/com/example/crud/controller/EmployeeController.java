package com.example.crud.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.example.crud.EmployeeNotFoundException;
import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;
import com.example.crud.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EmployeeService service;
	
	
	@PostMapping
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
		Employee employeeRequest=modelMapper.map(employeeDto,Employee.class);
		Employee employee= service.saveEmployee(employeeRequest);
		EmployeeDto employeeResponse=modelMapper.map(employee,EmployeeDto.class);
		
		return new ResponseEntity<EmployeeDto>(employeeResponse,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public List<EmployeeDto> findAllEmployees(){
		return service.getEmployees().stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
				.collect(Collectors.toList());

	}
	@GetMapping("/byId/{id}")
	public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable(name="id") int id)  {
		Employee employee=service.getEmployeeById(id);
		EmployeeDto employeeResponse=modelMapper.map(employee,EmployeeDto.class);
		return ResponseEntity.ok().body(employeeResponse);
	}
	@GetMapping("/byName/{name}")
	public ResponseEntity<EmployeeDto> findEmployeeByName(@PathVariable(name="name") String name) {
		Employee employee=service.getEmployeeByName(name);
		EmployeeDto employeeResponse=modelMapper.map(employee,EmployeeDto.class);
		return ResponseEntity.ok().body(employeeResponse);
	}
	@PutMapping
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employeeRequest=modelMapper.map(employeeDto,Employee.class);
		Employee employee=service.updateEmployee(employeeRequest);
		EmployeeDto employeeResponse=modelMapper.map(employee,EmployeeDto.class);
		return  ResponseEntity.ok().body(employeeResponse);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable(name="id") int id) {
		try {
		service.deleteEmployee(id);
		return ResponseEntity.noContent().build();
		}catch(EmployeeNotFoundException e) {
		 return ResponseEntity.notFound().build();
		}
	}
	
	
}
