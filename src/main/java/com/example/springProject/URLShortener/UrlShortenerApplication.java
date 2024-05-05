package com.example.springProject.URLShortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		URLShortenerController obj = new URLShortenerController();
		obj.postURL("vsd");
		//SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
