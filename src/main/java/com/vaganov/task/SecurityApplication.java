package com.vaganov.task;

import com.vaganov.task.auth.AuthenticationService;
import com.vaganov.task.auth.RegisterRequest;
import com.vaganov.task.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("User")
					.lastname("User")
					.email("user@mail.com")
					.password("password")
					.role(Role.USER)
					.build();
			System.out.println("User token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Executor")
					.lastname("Executor")
					.email("executor@mail.com")
					.password("password")
					.role(Role.EXECUTOR)
					.build();
			System.out.println("Executor token: " + service.register(manager).getAccessToken());

		};
	}
}
