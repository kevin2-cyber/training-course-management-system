package com.mukeju.nomad.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Course {
    private Integer courseNo;
    private String courseName;
    private String courseDescription;
    private Date startDate;
    private Date endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxDelegates;
    private Boolean confirmed;
    private Integer delivererEmployeeNo;
    private Integer courseTypeNo;
}
