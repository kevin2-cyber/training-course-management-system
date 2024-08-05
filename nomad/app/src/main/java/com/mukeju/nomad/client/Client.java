package com.mukeju.nomad.client;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client extends BaseObservable {
    @SerializedName("clientNo")
    @Expose
    private Long clientNo;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("clientAddress")
    @Expose
    private String clientAddress;
    @SerializedName("clientPhone")
    @Expose
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

    @Bindable
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
        notifyPropertyChanged(BR.clientName);
    }

    @Bindable
    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
        notifyPropertyChanged(BR.clientAddress);
    }

    @Bindable
    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
        notifyPropertyChanged(BR.clientPhone);
    }

    @NonNull
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
