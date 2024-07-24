package com.kimikevin.nomad_backend.repository;

import com.kimikevin.nomad_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientEmail(String email);
}
