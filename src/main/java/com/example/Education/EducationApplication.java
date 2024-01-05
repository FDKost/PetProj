package com.example.Education;

import com.example.Education.Repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ServletComponentScan
public class EducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}

	@Bean
	ApplicationRunner dataLoaderUser(UserRepository repo, PasswordEncoder encoder) {
		return args -> {
			repo.save(User.builder().username("pizza").password(encoder.encode("123")).build());
		};


	}
}

