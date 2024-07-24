package com.kimikevin.nomad_backend.repository;

import com.kimikevin.nomad_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeEmail(String email);
}
