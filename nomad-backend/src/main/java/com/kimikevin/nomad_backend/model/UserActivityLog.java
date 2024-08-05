package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "useractivitylog")
public class UserActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @Column(name = "userId")
    private String userId;
    @Column(name = "actionType")
    private String actionType;
    @Column(name = "tableName")
    private String tableName;
    @Column(name = "recordId")
    private Long recordId;
    @Column(name = "actionDate")
    private Date actionDate;

    public UserActivityLog() {}

    public UserActivityLog(String userId,
                           String actionType,
                           String tableName,
                           Long recordId,
                           Date actionDate) {
        this.userId = userId;
        this.actionType = actionType;
        this.tableName = tableName;
        this.recordId = recordId;
        this.actionDate = actionDate;
    }

    public UserActivityLog(Long logId,
                           String userId,
                           String actionType,
                           String tableName,
                           Long recordId,
                           Date actionDate) {
        this.logId = logId;
        this.userId = userId;
        this.actionType = actionType;
        this.tableName = tableName;
        this.recordId = recordId;
        this.actionDate = actionDate;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    @Override
    public String toString() {
        return "UserActivityLog{" +
                "logId=" + logId +
                ", userId='" + userId + '\'' +
                ", actionType='" + actionType + '\'' +
                ", tableName='" + tableName + '\'' +
                ", recordId=" + recordId +
                ", actionDate=" + actionDate +
                '}';
    }
}
