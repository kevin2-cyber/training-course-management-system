package com.kimikevin.nomad_backend.course;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course_no")
    @SequenceGenerator(name = "seq_course_no", sequenceName = "seq_course_no", allocationSize = 1)
    @Column(name = "courseNo")
    private Long courseNo;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "courseDescription")
    private String courseDescription;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;
    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "endTime")
    private LocalDateTime endTime;
    @Column(name= "maxDelegates")
    private Integer maxDelegates;
    @Column(name = "confirmed")
    private Character confirmed;
    @Column(name = "delivererEmployeeNo")
    private Long delivererEmployeeNo;
    @Column(name = "courseTypeNo")
    private Long courseTypeNo;

    public Course() {}

    public Course(String courseName,
                  String courseDescription,
                  Date startDate,
                  Date endDate,
                  LocalDateTime startTime,
                  LocalDateTime endTime,
                  Integer maxDelegates,
                  Character confirmed) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxDelegates = maxDelegates;
        this.confirmed = confirmed;
    }

    public Course(Long courseNo,
                  String courseName,
                  String courseDescription,
                  Date startDate,
                  Date endDate,
                  LocalDateTime startTime,
                  LocalDateTime endTime,
                  Integer maxDelegates,
                  Character confirmed) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxDelegates = maxDelegates;
        this.confirmed = confirmed;
    }

    public Long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxDelegates() {
        return maxDelegates;
    }

    public void setMaxDelegates(Integer maxDelegates) {
        this.maxDelegates = maxDelegates;
    }

    public Character getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Character confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNo=" + courseNo +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", maxDelegates=" + maxDelegates +
                ", confirmed=" + confirmed +
                '}';
    }
}
