package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceNo;
    @Column
    private Date dateRaised;
    @Column
    private Date datePaid;
    @Column
    private String creditCardNo;
    @Column
    private String HoldersName;
    @Column
    private Date expiryDate;
    @Column
    private Long registrationNo;
    @Column
    private Long pMethodNo;
}
