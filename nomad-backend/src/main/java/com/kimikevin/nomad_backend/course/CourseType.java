package com.kimikevin.nomad_backend.course;

import jakarta.persistence.*;

@Entity
@Table(name = "coursetype")
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course_type_no")
    @SequenceGenerator(name = "seq_course_type_no", sequenceName = "seq_course_type_no", allocationSize = 1)
    @Column(name = "courseTypeNo")
    private int courseTypeNo;
    @Column(name = "courseTypeDescription")
    private String courseTypeDescription;

    public CourseType() {}

    public CourseType(String courseTypeDescription) {
        this.courseTypeDescription = courseTypeDescription;
    }

    public int getCourseTypeNo() {
        return courseTypeNo;
    }

    public void setCourseTypeNo(int courseTypeNo) {
        this.courseTypeNo = courseTypeNo;
    }

    public String getCourseTypeDescription() {
        return courseTypeDescription;
    }

    public void setCourseTypeDescription(String courseTypeDescription) {
        this.courseTypeDescription = courseTypeDescription;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeNo=" + courseTypeNo +
                ", courseTypeDescription='" + courseTypeDescription + '\'' +
                '}';
    }
}