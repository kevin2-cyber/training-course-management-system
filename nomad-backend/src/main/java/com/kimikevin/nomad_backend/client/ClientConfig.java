package com.kimikevin.nomad_backend.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
        return args -> {
            Client abena = new Client(
                    "Abena",
                    "Sunyani",
                    "0344888000999"
            );
            Client mariam = new Client(
                    "Mariam",
                    "Kumasi",
                    "0344888000999"
            );
            Client kwaku = new Client(
                    "Kwaku",
                    "Tamale",
                    "0344888000999"
            );
            clientRepository.saveAll(List.of(abena, mariam, kwaku));
        };
    }
}
