package com.kimikevin.nomad_backend.delegate;

import jakarta.persistence.*;

@Entity
@Table(name = "delegate")
public class Delegate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_delegate_no")
    @SequenceGenerator(name = "seq_delegate_no", sequenceName = "seq_delegate_no", allocationSize = 1)
    @Column(name = "delegateNo")
    private Long delegateNo;
    @Column(name = "delegateTitle")
    private String delegateTitle;
    @Column(name = "delegateFName")
    private String delegateFName;
    @Column(name = "delegateLName")
    private String delegateLName;
    @Column(name = "delegateStreet")
    private String delegateStreet;
    @Column(name = "delegateCity")
    private String delegateCity;
    @Column(name = "delegateState")
    private String delegateState;
    @Column(name = "delegateZipCode")
    private String delegateZipCode;
    @Column(name = "attTelNo")
    private String attTelNo;
    @Column(name = "attFaxNo")
    private String attFaxNo;
    @Column(name = "attEmailAddress")
    private String attEmailAddress;
    @Column(name = "clientNo")
    private Long clientNo;

    public Delegate() {}

    public Delegate(String delegateTitle,
                    String delegateFName,
                    String delegateLName,
                    String delegateStreet,
                    String delegateCity,
                    String delegateState,
                    String delegateZipCode,
                    String attTelNo,
                    String attFaxNo,
                    String attEmailAddress,
                    Long clientNo) {
        this.delegateTitle = delegateTitle;
        this.delegateFName = delegateFName;
        this.delegateLName = delegateLName;
        this.delegateStreet = delegateStreet;
        this.delegateCity = delegateCity;
        this.delegateState = delegateState;
        this.delegateZipCode = delegateZipCode;
        this.attTelNo = attTelNo;
        this.attFaxNo = attFaxNo;
        this.attEmailAddress = attEmailAddress;
        this.clientNo = clientNo;
    }

    public Delegate(Long delegateNo,
                    String delegateTitle,
                    String delegateFName,
                    String delegateLName,
                    String delegateStreet,
                    String delegateCity,
                    String delegateState,
                    String delegateZipCode,
                    String attTelNo,
                    String attFaxNo,
                    String attEmailAddress,
                    Long clientNo) {
        this.delegateNo = delegateNo;
        this.delegateTitle = delegateTitle;
        this.delegateFName = delegateFName;
        this.delegateLName = delegateLName;
        this.delegateStreet = delegateStreet;
        this.delegateCity = delegateCity;
        this.delegateState = delegateState;
        this.delegateZipCode = delegateZipCode;
        this.attTelNo = attTelNo;
        this.attFaxNo = attFaxNo;
        this.attEmailAddress = attEmailAddress;
        this.clientNo = clientNo;
    }

    public Long getDelegateNo() {
        return delegateNo;
    }

    public void setDelegateNo(Long delegateNo) {
        this.delegateNo = delegateNo;
    }

    public String getDelegateTitle() {
        return delegateTitle;
    }

    public void setDelegateTitle(String delegateTitle) {
        this.delegateTitle = delegateTitle;
    }

    public String getDelegateFName() {
        return delegateFName;
    }

    public void setDelegateFName(String delegateFName) {
        this.delegateFName = delegateFName;
    }

    public String getDelegateLName() {
        return delegateLName;
    }

    public void setDelegateLName(String delegateLName) {
        this.delegateLName = delegateLName;
    }

    public String getDelegateStreet() {
        return delegateStreet;
    }

    public void setDelegateStreet(String delegateStreet) {
        this.delegateStreet = delegateStreet;
    }

    public String getDelegateCity() {
        return delegateCity;
    }

    public void setDelegateCity(String delegateCity) {
        this.delegateCity = delegateCity;
    }

    public String getDelegateState() {
        return delegateState;
    }

    public void setDelegateState(String delegateState) {
        this.delegateState = delegateState;
    }

    public String getDelegateZipCode() {
        return delegateZipCode;
    }

    public void setDelegateZipCode(String delegateZipCode) {
        this.delegateZipCode = delegateZipCode;
    }

    public String getAttTelNo() {
        return attTelNo;
    }

    public void setAttTelNo(String attTelNo) {
        this.attTelNo = attTelNo;
    }

    public String getAttFaxNo() {
        return attFaxNo;
    }

    public void setAttFaxNo(String attFaxNo) {
        this.attFaxNo = attFaxNo;
    }

    public String getAttEmailAddress() {
        return attEmailAddress;
    }

    public void setAttEmailAddress(String attEmailAddress) {
        this.attEmailAddress = attEmailAddress;
    }

    public Long getClientNo() {
        return clientNo;
    }

    public void setClientNo(Long clientNo) {
        this.clientNo = clientNo;
    }

    @Override
    public String toString() {
        return "Delegate{" +
                "delegateNo=" + delegateNo +
                ", delegateTitle='" + delegateTitle + '\'' +
                ", delegateFName='" + delegateFName + '\'' +
                ", delegateLName='" + delegateLName + '\'' +
                ", delegateStreet='" + delegateStreet + '\'' +
                ", delegateCity='" + delegateCity + '\'' +
                ", delegateState='" + delegateState + '\'' +
                ", delegateZipCode='" + delegateZipCode + '\'' +
                ", attTelNo='" + attTelNo + '\'' +
                ", attFaxNo='" + attFaxNo + '\'' +
                ", attEmailAddress='" + attEmailAddress + '\'' +
                ", clientNo=" + clientNo +
                '}';
    }
}
