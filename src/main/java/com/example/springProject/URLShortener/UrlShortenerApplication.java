package com.example.springProject.URLShortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.springProject.URLShortener")
public class UrlShortenerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UrlShortenerApplication.class, args);
	}
}
