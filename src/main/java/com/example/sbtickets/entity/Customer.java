package com.example.sbtickets.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "cmt")
    private String cmt;
    @Column(name = "address")
    private String address;
    @Column(name = "birth_Day")
    private Date birthDay;
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "account_id")
    private User user;

    public Customer(Integer id, String fullName, String cmt, String address, Date birthDay, String email, User user) {
        this.id = id;
        this.fullName = fullName;
        this.cmt = cmt;
        this.address = address;
        this.birthDay = birthDay;
        this.email = email;
        this.user = user;
    }

    public Customer(String fullName, String cmt, String address, Date birthDay, String email, User user) {
        this.fullName = fullName;
        this.cmt = cmt;
        this.address = address;
        this.birthDay = birthDay;
        this.email = email;
        this.user = user;
    }

    public Customer(String fullName, String cmt, String address, String email, Date birthDay) {
        this.id = id;
        this.fullName = fullName;
        this.cmt = cmt;
        this.address = address;
        this.email = email;
        this.birthDay = birthDay;
    }

    public Customer() {
    }






    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCmt() {
        return cmt;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
