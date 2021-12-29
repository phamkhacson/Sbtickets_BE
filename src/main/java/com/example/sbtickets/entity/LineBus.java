package com.example.sbtickets.entity;

import javax.persistence.*;

@Entity
@Table(name = "line_bus")
public class LineBus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "first_id")
    private TripBusAddress firstPoint;
    @OneToOne
    @JoinColumn(name = "last_id")
    private TripBusAddress lastPoint;
    @Column(name = "length")
    private Integer length;
    @Column(name = "complexity")
    private Integer complexity;

    public LineBus () {

    }

    public LineBus(Integer id, TripBusAddress firstPoint, TripBusAddress lastPoint, Integer length, Integer complexity) {
        this.id = id;
        this.firstPoint = firstPoint;
        this.lastPoint = lastPoint;
        this.length = length;
        this.complexity = complexity;
    }

    public LineBus(TripBusAddress firstPoint, TripBusAddress lastPoint, Integer length, Integer complexity) {
        this.firstPoint = firstPoint;
        this.lastPoint = lastPoint;
        this.length = length;
        this.complexity = complexity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TripBusAddress getfirstPoint() {
        return firstPoint;
    }

    public void setfirstPoint(TripBusAddress firstPoint) {
        this.firstPoint = firstPoint;
    }

    public TripBusAddress getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(TripBusAddress lastPoint) {
        this.lastPoint = lastPoint;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }
}