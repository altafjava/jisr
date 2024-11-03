package com.jisr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JisrApplication {

	public static void main(String[] args) {
		SpringApplication.run(JisrApplication.class, args);
		log.info("------------- APPLICATION STARTED -------------");
	}

}
