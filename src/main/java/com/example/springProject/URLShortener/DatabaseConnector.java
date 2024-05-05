package com.example.springProject.URLShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnector {
    private final DataSourceConfig dataSourceConfig;

    @Autowired
    public DatabaseConnector(DataSourceConfig dataSourceConfig){
        this.dataSourceConfig = dataSourceConfig;
    }

    public void connectToDatabase(){
        String url = dataSourceConfig.getUrl();
        String username = dataSourceConfig.getUsername();
        String password = dataSourceConfig.getPassword();
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}

