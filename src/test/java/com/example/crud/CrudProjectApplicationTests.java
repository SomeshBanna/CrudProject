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
		assertEquals(true,true);
	}

}
