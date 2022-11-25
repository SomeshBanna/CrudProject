package com.example.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CrudProjectExampleApplicationTests {
    @Autowired
    private EmployeeRepository employeeRepository;

	public static Logger logger=LoggerFactory.getLogger(CrudProjectExampleApplicationTests.class);
	@Test
	void contextLoads() {
		logger.info("TEST CASE EXECUTING...");
		logger.info("TEST CASE EXECUTING 2...");
		logger.info("TEST CASE EXECUTING 3...");

		logger.info("Test 4");


		assertEquals(true,true);
	}
   /*@Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        Employee employee=new Employee();
        
        employee.setName("suresh");
        employee.setSalary(45000);
        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }
*/
    @Test
    @Order(2)
    public void getEmployeeTest(){

        Employee employee = employeeRepository.findById(1).get();

        Assertions.asserthat(employee.getId()).isEqualTo(1);

    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        Employee employee = employeeRepository.findById(1).get();

        employee.setName("Rahul");

        Employee employeeUpdated =  employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Rahul");

    }

    

}
