package com.example.sbtickets.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mtripbus_driver")
public class TripBusDriver implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="tripbus_id", nullable=false)
    private TripBus tripbus;
    @ManyToOne
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;
    @Column(name = "role_car")
    private String roleCar;
    @Column(name = "wages")
    private Double  wages;
    @Column(name = "date")
    private Date date;
    @Column(name = "scrap_date_time")
    private String scrapDateTime;

    public TripBusDriver() {
    }

    public TripBusDriver(Integer id, TripBus tripbus, Driver driver, String roleCar, Double wages, Date date, String scrapDateTime) {
        this.id = id;
        this.tripbus = tripbus;
        this.driver = driver;
        this.roleCar = roleCar;
        this.wages = wages;
        this.date = date;
        this.scrapDateTime = scrapDateTime;
    }

    public TripBusDriver(TripBus tripbus, Driver driver, String roleCar, Double wages, Date date, String scrapDateTime) {
        this.tripbus = tripbus;
        this.driver = driver;
        this.roleCar = roleCar;
        this.wages = wages;
        this.date = date;
        this.scrapDateTime = scrapDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TripBus getTripbus() {
        return tripbus;
    }

    public void setTripbus(TripBus tripbus) {
        this.tripbus = tripbus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getRoleCar() {
        return roleCar;
    }

    public void setRoleCar(String roleCar) {
        this.roleCar = roleCar;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScrapDateTime() {
        return scrapDateTime;
    }

    public void setScrapDateTime(String scrapDateTime) {
        this.scrapDateTime = scrapDateTime;
    }
}
