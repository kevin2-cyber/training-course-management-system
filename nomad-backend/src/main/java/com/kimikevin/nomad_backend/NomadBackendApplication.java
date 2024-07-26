package com.kimikevin.nomad_backend;

import com.kimikevin.nomad_backend.model.Client;
import com.kimikevin.nomad_backend.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NomadBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NomadBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
		return args -> {
			Client oClient = new Client(
					clientRepository.getNextSequenceValue(),
					"Kwame",
					"Koforidua",
					"0344888000999"
			);
			clientRepository.save(oClient);
		};
	}

}
