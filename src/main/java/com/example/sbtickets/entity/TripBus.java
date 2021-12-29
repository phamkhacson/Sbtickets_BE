package com.example.sbtickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tripbus")
public class TripBus{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="bus_id", nullable=false)
    private Bus bus;
    @ManyToOne
    @JoinColumn(name="linebus_id", nullable=false)
    private LineBus lineBus;
    @Column(name = "number_guest")
    private Integer numberGuest;
    @Column(name = "price_trip")
    private Double priceTrip;
    @Column(name = "time_trip")
    private Date timeTrip;

    public TripBus() {
    }

    public TripBus(Integer id, Bus bus, LineBus lineBus, Integer numberGuest, Double priceTrip, Date timeTrip) {
        this.id = id;
        this.bus = bus;
        this.lineBus = lineBus;
        this.numberGuest = numberGuest;
        this.priceTrip = priceTrip;
        this.timeTrip = timeTrip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public LineBus getLineBus() {
        return lineBus;
    }

    public void setLineBus(LineBus lineBus) {
        this.lineBus = lineBus;
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
}
