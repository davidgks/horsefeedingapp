package com.example.mortalcommand.horsefeedingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class from where the application can get started.
 * It included the main method to start the application running.
 */
@SpringBootApplication
public class HorsefeedingappApplication {

	/**
	 * This method starts the application.
	 * @param args Command-line arguments passed to the application. These arguments can be used
	 *             to configure or customize the application's behavior.
	 */
	public static void main(String[] args) {
		SpringApplication.run(HorsefeedingappApplication.class, args);
	}

}
