package com.kimikevin.nomad_backend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_location_no")
    @SequenceGenerator(name = "seq_location_no", sequenceName = "seq_location_no", allocationSize = 1)
    @Column(name = "locationNo")
    private Long locationNo;
    @Column(name = "locationName")
    private String locationName;
    @Column(name = "maxSize")
    private Integer maxSize;

    public Location() {}

    public Location(String locationName, Integer maxSize) {
        this.locationName = locationName;
        this.maxSize = maxSize;
    }

    public Location(Long locationNo, String locationName, Integer maxSize) {
        this.locationNo = locationNo;
        this.locationName = locationName;
        this.maxSize = maxSize;
    }

    public Long getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(Long locationNo) {
        this.locationNo = locationNo;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationNo=" + locationNo +
                ", locationName='" + locationName + '\'' +
                ", maxSize=" + maxSize +
                '}';
    }
}
