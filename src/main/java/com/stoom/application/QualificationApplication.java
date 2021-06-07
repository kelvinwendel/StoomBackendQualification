package com.stoom.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Responsible main class for initialize the API.
 */
@SpringBootApplication
public class QualificationApplication {

	/**
	 * Initialize the API.
	 *
	 * @param args
	 *   Arguments for initialization of API.
	 */
	public static void main(String[] args) {
		SpringApplication.run(QualificationApplication.class, args);
	}
}