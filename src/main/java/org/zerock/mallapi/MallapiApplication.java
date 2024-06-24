package org.zerock.mallapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MallapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallapiApplication.class, args);
	}

}
