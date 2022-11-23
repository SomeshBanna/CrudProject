package com.example.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudProjectExampleApplicationTests {
	public static Logger logger=LoggerFactory.getLogger(CrudProjectExampleApplicationTests.class);
	@Test
	void contextLoads() {
		logger.info("TEST CASE EXECUTING...");
		logger.info("TEST CASE EXECUTING 2...");
		logger.info("TEST CASE EXECUTING 3...");
<<<<<<< HEAD
		logger.info("Test 4");
=======
		logger.info("TEST CASE EXECUTING 4...");
>>>>>>> e4b3c1cdd15315299b59ef8e08322799d7877aec
		assertEquals(true,true);
	}

}
