package com.kimikevin.nomad_backend.repository;

import com.kimikevin.nomad_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT NEXTVAL('seq_client_no')", nativeQuery = true)
    Long getNextSequenceValue();
}
