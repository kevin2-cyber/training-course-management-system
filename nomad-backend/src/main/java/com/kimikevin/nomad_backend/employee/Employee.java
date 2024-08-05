package com.kimikevin.nomad_backend.employee;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_no")
    @SequenceGenerator(name = "seq_employee_no", sequenceName = "seq_employee_no", allocationSize = 1)
    @Column(name = "employeeNo")
    private Long employeeNo;

    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "employeeEmail")
    private String employeeEmail;
    @Column(name = "employeePhone")
    private String employeePhone;

    public Employee() {}

    public Employee(Long employeeNo, String employeeName, String employeeEmail, String employeePhone) {
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }

    public Employee(String employeeName, String employeeEmail, String employeePhone) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNo=" + employeeNo +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                '}';
    }
}
