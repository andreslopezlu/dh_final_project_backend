package com.dh.final_project_backend.repository.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionJDBC {
    private final static Logger logger = LogManager.getLogger(ConnectionJDBC.class);

    private String DB_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    public ConnectionJDBC() {
        this.DB_DRIVER = "org.h2.Driver";
        this.DB_URL = "jdbc:h2:tcp://localhost/~/test;INIT=RUNSCRIPT FROM '/Users/andreslopezlu/Library/Mobile Documents/com~apple~CloudDocs/Documents/dh_tech_developer/dh/8_backend1/scripts/trabajo_integrador/create.sql'";
        this.DB_USER = "sa";
        this.DB_PASSWORD = "";
    }

    public ConnectionJDBC(String DB_DRIVER, String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_DRIVER = DB_DRIVER;
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public Connection connectToDB(){
        Connection connection = null;

        try{
            Class.forName(DB_DRIVER);
            connection= DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.info("Se produjo una excepción: ClassNotFoundException");
            e.printStackTrace();
        }
        return connection;
    }
}
