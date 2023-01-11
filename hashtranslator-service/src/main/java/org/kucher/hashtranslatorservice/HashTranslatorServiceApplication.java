package org.kucher.hashtranslatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HashTranslatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HashTranslatorServiceApplication.class, args);
	}

}
