package com.kimikevin.nomad_backend.repository;

import com.kimikevin.nomad_backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
