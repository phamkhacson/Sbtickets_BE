package com.example.sbtickets.bean;

import java.util.Date;
public class RevenueBusBean {
    public Integer busId;
    private Integer carNumber;
    private Integer tripBusId;
    private Integer numberGuest;
    public Double priceTrip;
    private Date timeTrip;
    private Double revenue;

    public RevenueBusBean() {
    }

    public RevenueBusBean(Integer busId, Integer carNumber, Integer tripBusId, Integer numberGuest, Double priceTrip, Date timeTrip, Double revenue) {
        this.busId = busId;
        this.carNumber = carNumber;
        this.tripBusId = tripBusId;
        this.numberGuest = numberGuest;
        this.priceTrip = priceTrip;
        this.timeTrip = timeTrip;
        this.revenue = revenue;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getTripBusId() {
        return tripBusId;
    }

    public void setTripBusId(Integer tripBusId) {
        this.tripBusId = tripBusId;
    }

    public Integer getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(Integer numberGuest) {
        this.numberGuest = numberGuest;
    }

    public Double getPriceTrip() {
        return priceTrip;
    }

    public void setPriceTrip(Double priceTrip) {
        this.priceTrip = priceTrip;
    }

    public Date getTimeTrip() {
        return timeTrip;
    }

    public void setTimeTrip(Date timeTrip) {
        this.timeTrip = timeTrip;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
