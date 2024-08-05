package com.kimikevin.nomad_backend.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
        return args -> {
            Client oClient = new Client(
                    "Kwame",
                    "Koforidua",
                    "0344888000999"
            );
            clientRepository.save(oClient);
        };
    }
}
