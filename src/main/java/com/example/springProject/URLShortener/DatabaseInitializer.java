package com.example.springProject.URLShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DatabaseConnector databaseConnector;

    @Autowired
    public DatabaseInitializer(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public void run(String... args) throws Exception {
        databaseConnector.connectToDatabase();
    }
}
