package com.kimikevin.nomad_backend.repository;

import com.kimikevin.nomad_backend.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {}
