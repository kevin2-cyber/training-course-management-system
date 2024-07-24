package com.kimikevin.nomad_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coursetype")
public record CourseType(@Id int courseTypeNo, @Column String courseTypeDescription) {}