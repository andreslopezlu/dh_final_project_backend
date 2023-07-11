package com.dh.final_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		File log4jfile = new File("./log4j2.properties");
		PropertyConfigurator.configure(log4jfile.getAbsolutePath());
	}

}
