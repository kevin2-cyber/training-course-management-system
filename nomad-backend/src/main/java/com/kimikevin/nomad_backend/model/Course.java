package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseNo;
    @Column
    private String courseName;
    @Column
    private String courseDescription;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @Column
    private int maxDelegates;
    @Column
    private char confirmed;
}
