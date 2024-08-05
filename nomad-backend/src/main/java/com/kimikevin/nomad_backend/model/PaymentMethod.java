package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "paymentmethod")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment_method_no")
    @SequenceGenerator(name = "seq_payment_method_no", sequenceName = "seq_payment_method_no", allocationSize = 1)
    @Column(name = "pMethodNo")
    private Long pMethodNo;
    @Column(name = "methodName")
    private String methodName;

    public PaymentMethod() {}

    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    public PaymentMethod(Long pMethodNo, String methodName) {
        this.pMethodNo = pMethodNo;
        this.methodName = methodName;
    }

    public Long getPMethodNo() {
        return pMethodNo;
    }

    public void setPMethodNo(Long pMethodNo) {
        this.pMethodNo = pMethodNo;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "pMethodNo=" + pMethodNo +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
