package com.kimikevin.nomad_backend.registration;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_registration_no")
    @SequenceGenerator(name = "seq_registration_no", sequenceName = "seq_registration_no", allocationSize = 1)
    @Column(name = "registrationNo")
    private Long registrationNo;
    @Column(name = "registrationDate")
    private LocalDate registrationDate;
    @Column(name = "delegateNo")
    private Long delegateNo;
    @Column(name = "courseFeeNo")
    private Long courseFeeNo;
    @Column(name = "registerEmployeeNo")
    private Long registerEmployeeNo;
    @Column(name = "courseNo")
    private Long courseNo;

    public Registration() {}

    public Registration(LocalDate registrationDate,
                        Long delegateNo,
                        Long courseFeeNo,
                        Long registerEmployeeNo,
                        Long courseNo) {
        this.registrationDate = registrationDate;
        this.delegateNo = delegateNo;
        this.courseFeeNo = courseFeeNo;
        this.registerEmployeeNo = registerEmployeeNo;
        this.courseNo = courseNo;
    }

    public Registration(Long registrationNo,
                        LocalDate registrationDate,
                        Long delegateNo,
                        Long courseFeeNo,
                        Long registerEmployeeNo,
                        Long courseNo) {
        this.registrationNo = registrationNo;
        this.registrationDate = registrationDate;
        this.delegateNo = delegateNo;
        this.courseFeeNo = courseFeeNo;
        this.registerEmployeeNo = registerEmployeeNo;
        this.courseNo = courseNo;
    }

    public Long getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(Long registrationNo) {
        this.registrationNo = registrationNo;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getDelegateNo() {
        return delegateNo;
    }

    public void setDelegateNo(Long delegateNo) {
        this.delegateNo = delegateNo;
    }

    public Long getCourseFeeNo() {
        return courseFeeNo;
    }

    public void setCourseFeeNo(Long courseFeeNo) {
        this.courseFeeNo = courseFeeNo;
    }

    public Long getRegisterEmployeeNo() {
        return registerEmployeeNo;
    }

    public void setRegisterEmployeeNo(Long registerEmployeeNo) {
        this.registerEmployeeNo = registerEmployeeNo;
    }

    public Long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationNo=" + registrationNo +
                ", registrationDate=" + registrationDate +
                ", delegateNo=" + delegateNo +
                ", courseFeeNo=" + courseFeeNo +
                ", registerEmployeeNo=" + registerEmployeeNo +
                ", courseNo=" + courseNo +
                '}';
    }
}
