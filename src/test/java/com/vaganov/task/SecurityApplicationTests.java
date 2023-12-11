package com.vaganov.task;

import com.vaganov.task.task.Task;
import com.vaganov.task.task.TaskController;
import com.vaganov.task.task.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private TaskController controller;


	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

}
