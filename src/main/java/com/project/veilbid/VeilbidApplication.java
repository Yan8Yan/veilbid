package com.project.veilbid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VeilbidApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeilbidApplication.class, args);
	}

}
