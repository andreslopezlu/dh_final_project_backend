package com.dh.final_project_backend.utils;

import com.dh.final_project_backend.exceptions.GlobalExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class LogFileCleaner implements ApplicationRunner {

    private final String logFilePath = "./logfile.log";

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @Override
    public void run(ApplicationArguments args) {
        try (FileWriter fileWriter = new FileWriter(logFilePath)) {
            fileWriter.write("");
        } catch (IOException e) {
            logger.info(e.getStackTrace().toString());
        }
    }
}
