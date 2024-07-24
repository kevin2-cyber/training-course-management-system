package com.kimikevin.nomad_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
    @Id
    private long locationNo;
    @Column
    private String locationName;
    @Column
    private int maxSize;
}
