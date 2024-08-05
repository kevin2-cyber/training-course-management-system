package com.kimikevin.nomad_backend.client;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client_no")
    @SequenceGenerator(name = "seq_client_no", sequenceName = "seq_client_no", allocationSize = 1)
    @Column(name = "clientNo")
    private Long clientNo;
    @Column(name = "clientName")
    private String clientName;
    @Column(name = "clientAddress")
    private String clientAddress;
    @Column(name = "clientPhone")
    private String clientPhone;

    public Client() {}

    public Client(Long clientNo, String clientName, String clientAddress, String clientPhone) {
        this.clientNo = clientNo;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientPhone = clientPhone;
    }

    public Client(String clientName, String clientAddress, String clientPhone) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientPhone = clientPhone;
    }

    public Long getClientNo() {
        return clientNo;
    }

    public void setClientNo(Long clientNo) {
        this.clientNo = clientNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientNo=" + clientNo +
                ", clientName='" + clientName + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                '}';
    }
}