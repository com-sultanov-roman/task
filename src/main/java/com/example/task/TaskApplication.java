package com.example.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class TaskApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TaskApplication.class, args);
	}

}
