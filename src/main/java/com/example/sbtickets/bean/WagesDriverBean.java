package com.example.sbtickets.bean;

import java.sql.Date;

public class WagesDriverBean {
    private Integer tripBusId;
    private String driverName;
    private Double fixedSalary;
    private Double wages;
    private String roleCar;
    private String scrapDateTime;

    public WagesDriverBean() {
    }

    public WagesDriverBean(Integer tripBusId, String driverName, Double fixedSalary, Double wages, String roleCar, String scrapDateTime) {
        this.tripBusId = tripBusId;
        this.driverName = driverName;
        this.fixedSalary = fixedSalary;
        this.wages = wages;
        this.roleCar = roleCar;
        this.scrapDateTime = scrapDateTime;
    }

    public Integer getTripBusId() {
        return tripBusId;
    }

    public void setTripBusId(Integer tripBusId) {
        this.tripBusId = tripBusId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(Double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public String getRoleCar() {
        return roleCar;
    }

    public void setRoleCar(String roleCar) {
        this.roleCar = roleCar;
    }

    public String getScrapDateTime() {
        return scrapDateTime;
    }

    public void setScrapDateTime(String scrapDateTime) {
        this.scrapDateTime = scrapDateTime;
    }
}
