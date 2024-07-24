package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationNo;
    @Column
    private Date registrationDate;
    @Column
    private Long delegateNo;
    @Column
    private Long courseFeeNo;
    @Column
    private Long registerEmployeeNo;
    @Column
    private Long courseNo;
}
