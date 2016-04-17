package com.jademy.concediiapp;

import java.io.InputStream;
import java.util.Properties;

/**
 * singleton to load admin database connection credentials from property file
 */
public class AdminDbCred {

    private AdminDbCred singleton = new AdminDbCred();

    private String db;
    private String dbUsername;
    private String dbPassword;

    public String getDb() {
        return db;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public AdminDbCred getInstance() {
        return singleton;
    }

    private AdminDbCred() {
        try {
            //load the properties file
            Properties props = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("database.properties");
            props.load(input);

            this.db = props.getProperty("db");
            this.dbUsername = props.getProperty("dbUser");
            this.dbPassword = props.getProperty("dbPassword");

        } catch (Exception e) {
            System.out.println("loadDbCredential() failed!*****************************************************");
            e.printStackTrace();
        }
    }
}
