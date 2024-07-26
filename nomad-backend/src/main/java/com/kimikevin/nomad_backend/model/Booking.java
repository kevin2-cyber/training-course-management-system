package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingNo;
    @Column
    private Date bookingDate;
    @Column
    private int locationNo;
    @Column
    private int courseNo;
    @Column
    private int bookingEmployeeNo;
}
