package com.serittec.application.client;

import com.serittec.application.client.domain.TipoUsuario;
import com.serittec.application.client.domain.User;
import com.serittec.application.client.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
			repository.save(new User(1L,"admin",new BCryptPasswordEncoder(12).encode("123456"),null, TipoUsuario.ADMIN));
		};
	}
}
