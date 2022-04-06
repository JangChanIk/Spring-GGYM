package com.kh.ggym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringGgymApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGgymApplication.class, args);
	}

}
