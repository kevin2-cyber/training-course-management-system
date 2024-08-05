package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_booking_no")
    @SequenceGenerator(name = "seq_booking_no", sequenceName = "seq_booking_no", allocationSize = 1)
    @Column(name = "bookingNo")
    private Long bookingNo;
    @Column(name = "bookingDate")
    private LocalDate bookingDate;
    @Column(name = "locationNo")
    private Long locationNo;
    @Column(name = "courseNo")
    private Long courseNo;
    @Column(name = "bookingEmployeeNo")
    private Long bookingEmployeeNo;

    public Booking() {}

    public Booking(LocalDate bookingDate,
                   Long locationNo,
                   Long courseNo,
                   Long bookingEmployeeNo) {
        this.bookingDate = bookingDate;
        this.locationNo = locationNo;
        this.courseNo = courseNo;
        this.bookingEmployeeNo = bookingEmployeeNo;
    }

    public Booking(Long bookingNo,
                   LocalDate bookingDate,
                   Long locationNo,
                   Long courseNo,
                   Long bookingEmployeeNo) {
        this.bookingNo = bookingNo;
        this.bookingDate = bookingDate;
        this.locationNo = locationNo;
        this.courseNo = courseNo;
        this.bookingEmployeeNo = bookingEmployeeNo;
    }

    public Long getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(Long bookingNo) {
        this.bookingNo = bookingNo;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(Long locationNo) {
        this.locationNo = locationNo;
    }

    public Long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    public Long getBookingEmployeeNo() {
        return bookingEmployeeNo;
    }

    public void setBookingEmployeeNo(Long bookingEmployeeNo) {
        this.bookingEmployeeNo = bookingEmployeeNo;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNo=" + bookingNo +
                ", bookingDate=" + bookingDate +
                ", locationNo=" + locationNo +
                ", courseNo=" + courseNo +
                ", bookingEmployeeNo=" + bookingEmployeeNo +
                '}';
    }
}
