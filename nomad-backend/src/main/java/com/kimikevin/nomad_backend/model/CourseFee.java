package com.kimikevin.nomad_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coursefee")
public class CourseFee {
    @Id
    private Long courseFeeNo;
    @Column
    private String feeDescription;
    @Column
    private int fee;
    @Column
    private Long courseNo;
}
