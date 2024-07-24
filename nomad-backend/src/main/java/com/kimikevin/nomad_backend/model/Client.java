package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "seq_client_no", allocationSize = 1)
    private Long clientNo;
    @Column
    private String clientName;
    @Column
    private String clientAddress;
    @Column
    private String clientPhone;

    @Column(unique = true, nullable = false)
    private String clientEmail;

    @Column(nullable = false)
    private String clientPassword;

}
