package com.kimikevin.nomad_backend.repository;

import com.kimikevin.nomad_backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {}
