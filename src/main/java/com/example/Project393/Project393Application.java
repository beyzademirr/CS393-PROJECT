package com.example.Project393;

import com.example.Project393.Model.Car;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@OpenAPIDefinition( info = @Info(version = "1.0",
		title = "Car Rental System",
		description = "Reserve cars"))
public class Project393Application {

	public static void main(String[] args) {
		SpringApplication.run(Project393Application.class, args);


	}



}
