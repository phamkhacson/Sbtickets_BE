package com.example.sbtickets.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mtripbus_customer")
public class TripBusCustomer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="tripbus_id", nullable=false)
    private TripBus tripbus;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
    @Column(name = "role_car")
    private Integer roleCar; // number of seat on tripbus

    public TripBusCustomer() {
    }

    public TripBusCustomer(Integer id, TripBus tripbus, Customer customer, Integer roleCar) {
        this.id = id;
        this.tripbus = tripbus;
        this.customer = customer;
        this.roleCar = roleCar;
    }

    public TripBusCustomer(TripBus tripbus, Customer customer, Integer roleCar) {
        this.tripbus = tripbus;
        this.customer = customer;
        this.roleCar = roleCar;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getRoleCar() {
        return roleCar;
    }

    public void setRoleCar(Integer roleCar) {
        this.roleCar = roleCar;
    }


}
