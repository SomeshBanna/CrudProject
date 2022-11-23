package com.example.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudProjectApplicationTests {
	public static Logger logger=LoggerFactory.getLogger(CrudProjectApplicationTests.class);
	@Test
	void contextLoads() {
		logger.info("TEST CASE EXECUTING...");
		logger.info("TEST CASE EXECUTING 2...");
		logger.info("TEST CASE EXECUTING 3...");
		logger.info("Test 4");
		assertEquals(true,true);
	}

}
