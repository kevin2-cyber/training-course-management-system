package com.kimikevin.nomad_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "delegate")
public class Delegate {
    @Id
    private Long delegateNo;
    @Column
    private String delegateTitle;
    @Column
    private String delegateFName;
    @Column
    private String delegateLName;
    @Column
    private String delegateStreet;
    @Column
    private String delegateCity;
    @Column
    private String delegateState;
    @Column
    private String delegateZipCode;
    @Column
    private String attTelNo;
    @Column
    private String attFaxNo;
    @Column
    private String attEmailAddress;
    @Column
    private Long clientNo;
}
