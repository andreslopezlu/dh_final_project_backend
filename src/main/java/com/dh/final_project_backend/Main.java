package com.dh.final_project_backend;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		File log4jfile = new File("./log4j.properties");
		PropertyConfigurator.configure(log4jfile.getAbsolutePath());
	}

}
