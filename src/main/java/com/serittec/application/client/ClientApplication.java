package com.serittec.application.client;

import com.serittec.application.client.domain.*;
import com.serittec.application.client.repository.ClienteRepository;
import com.serittec.application.client.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository repository, ClienteRepository clienteRepository) {
		return args -> {
			repository.save(new User(1L,"admin",new BCryptPasswordEncoder(12).encode("123456"),null, TipoUsuario.ADMIN));
			repository.save(new User(2L,"user",new BCryptPasswordEncoder(12).encode("123456"),null, TipoUsuario.USER));
			clienteRepository.save(Cliente.builder()
					.cpf("09876538409")
					.email(Arrays.asList(Email.builder().email("lala@gmail.com").build()))
					.endereco(Endereco.builder().logradouro("BH").bairro("SAVASSI").cep("73000000").localidade("A").complemento("APT").build())
					.telefone(Arrays.asList(Telefone.builder().numero("00000000000").tipo(Tipo.CELULAR).build()))
					.responsavel(1L)
					.nome("EDUARDO")
					.updatedAt(LocalDateTime.now())
					.build());
		};
	}
}
