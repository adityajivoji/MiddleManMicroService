package com.middleman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//A microservice developed in Java to process and authorize transactions. Multiple microservices can be setup in the microservice itself to perform various checks on the transaction details and each run independently. The application is scalable and does real time procecessing.
@SpringBootApplication
public class MiddlemanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddlemanApplication.class, args);
	}

}
