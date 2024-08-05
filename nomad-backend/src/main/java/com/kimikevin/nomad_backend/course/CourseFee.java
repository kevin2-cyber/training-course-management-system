package com.kimikevin.nomad_backend.course;

import jakarta.persistence.*;

@Entity
@Table(name = "coursefee")
public class CourseFee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coursefee_no")
    @SequenceGenerator(name = "seq_coursefee_no", sequenceName = "seq_coursefee_no", allocationSize = 1)
    @Column(name = "courseFeeNo")
    private Long courseFeeNo;
    @Column(name = "feeDescription")
    private String feeDescription;
    @Column(name = "fee")
    private Integer fee;
    @Column(name = "courseNo")
    private Long courseNo;

    public CourseFee() {}

    public CourseFee(String feeDescription,
                     Integer fee,
                     Long courseNo) {
        this.feeDescription = feeDescription;
        this.fee = fee;
        this.courseNo = courseNo;
    }

    public CourseFee(Long courseFeeNo,
                     String feeDescription,
                     Integer fee,
                     Long courseNo) {
        this.courseFeeNo = courseFeeNo;
        this.feeDescription = feeDescription;
        this.fee = fee;
        this.courseNo = courseNo;
    }

    public Long getCourseFeeNo() {
        return courseFeeNo;
    }

    public void setCourseFeeNo(Long courseFeeNo) {
        this.courseFeeNo = courseFeeNo;
    }

    public String getFeeDescription() {
        return feeDescription;
    }

    public void setFeeDescription(String feeDescription) {
        this.feeDescription = feeDescription;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    @Override
    public String toString() {
        return "CourseFee{" +
                "courseFeeNo=" + courseFeeNo +
                ", feeDescription='" + feeDescription + '\'' +
                ", fee=" + fee +
                ", courseNo=" + courseNo +
                '}';
    }
}
