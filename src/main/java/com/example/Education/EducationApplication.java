package com.example.Education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class EducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}

	/*@Bean
	ApplicationRunner dataLoaderUser(UserRepository repo, PasswordEncoder encoder) {
		return args -> {
			repo.save(User.builder().username("pizza").password(encoder.encode("123")).build());
		};


	}*/
}

