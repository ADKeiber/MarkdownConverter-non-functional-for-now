package com.adk.markdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class MarkdownNoteTakingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkdownNoteTakingAppApplication.class, args);
	}

}
