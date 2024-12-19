package com.idat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class ProjectEnotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEnotesApplication.class, args);
	}

}
