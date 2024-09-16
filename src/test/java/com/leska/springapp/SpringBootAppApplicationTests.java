package com.leska.springapp;

import com.leska.springapp.services.VacationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAppApplicationTests {

	@Autowired
	private VacationService vacationService;

	@Test
	void contextLoads() {
	}

}
