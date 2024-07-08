package com.mukeju.nomad.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Course {
    private int courseNo;
    private String courseName;
    private String courseDescription;
    private Date startDate;
    private Date endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxDelegates;
    private Boolean confirmed;
    private int delivererEmployeeNo;
    private int courseTypeNo;
}
