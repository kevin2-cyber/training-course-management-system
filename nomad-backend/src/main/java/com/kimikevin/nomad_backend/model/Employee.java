package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @SequenceGenerator(name = "employee_generator", sequenceName = "seq_employee_no", allocationSize = 1)
    private long employeeNo;

    @Column
    private String employeeName;
    @Column(unique = true, nullable = false)
    private String employeeEmail;
    @Column
    private String employeePhone;

    @Column(nullable = false)
    private String employeePassword;
}
