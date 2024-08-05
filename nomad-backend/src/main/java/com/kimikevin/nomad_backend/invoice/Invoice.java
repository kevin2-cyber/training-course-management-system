package com.kimikevin.nomad_backend.invoice;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_invoice_no")
    @SequenceGenerator(name = "seq_invoice_no", sequenceName = "seq_invoice_no", allocationSize = 1)
    @Column(name = "invoiceNo")
    private Long invoiceNo;
    @Column(name = "dateRaised")
    private LocalDate dateRaised;
    @Column(name = "datePaid")
    private Date datePaid;
    @Column(name = "creditCardNo")
    private String creditCardNo;
    @Column(name = "holdersName")
    private String holdersName;
    @Column(name = "expiryDate")
    private Date expiryDate;
    @Column(name = "registrationNo")
    private Long registrationNo;
    @Column(name = "pMethodNo")
    private Long pMethodNo;

    public Invoice() {}

    public Invoice(LocalDate dateRaised,
                   Date datePaid,
                   String creditCardNo,
                   String holdersName,
                   Date expiryDate,
                   Long registrationNo,
                   Long pMethodNo) {
        this.dateRaised = dateRaised;
        this.datePaid = datePaid;
        this.creditCardNo = creditCardNo;
        this.holdersName = holdersName;
        this.expiryDate = expiryDate;
        this.registrationNo = registrationNo;
        this.pMethodNo = pMethodNo;
    }

    public Invoice(Long invoiceNo,
                   LocalDate dateRaised,
                   Date datePaid,
                   String creditCardNo,
                   String holdersName,
                   Date expiryDate,
                   Long registrationNo,
                   Long pMethodNo) {
        this.invoiceNo = invoiceNo;
        this.dateRaised = dateRaised;
        this.datePaid = datePaid;
        this.creditCardNo = creditCardNo;
        this.holdersName = holdersName;
        this.expiryDate = expiryDate;
        this.registrationNo = registrationNo;
        this.pMethodNo = pMethodNo;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getDateRaised() {
        return dateRaised;
    }

    public void setDateRaised(LocalDate dateRaised) {
        this.dateRaised = dateRaised;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getHoldersName() {
        return holdersName;
    }

    public void setHoldersName(String holdersName) {
        holdersName = holdersName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(Long registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Long getPMethodNo() {
        return pMethodNo;
    }

    public void setPMethodNo(Long pMethodNo) {
        this.pMethodNo = pMethodNo;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNo=" + invoiceNo +
                ", dateRaised=" + dateRaised +
                ", datePaid=" + datePaid +
                ", creditCardNo='" + creditCardNo + '\'' +
                ", holdersName='" + holdersName + '\'' +
                ", expiryDate=" + expiryDate +
                ", registrationNo=" + registrationNo +
                ", pMethodNo=" + pMethodNo +
                '}';
    }
}
