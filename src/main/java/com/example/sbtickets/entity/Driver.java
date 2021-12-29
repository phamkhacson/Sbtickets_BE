package com.example.sbtickets.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cmt")
    private String nationalId;
    @Column(name = "driver_name")
    private String name;
    @Column(name = "code_license")
    private String codeLicense;
    @Column(name = "type_license")
    private String typeLicense;
    @Column(name = "address")
    private String address;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "senority")
    private Integer seniority;
    @Column(name = "image")
    private String image;
    @Column(name = "fixed_salary")
    private Double fixedSalary;

    public Driver() {
    }

    public Driver(String nationalId, String name, String codeLicense, String typeLicense, String address, Date dob, Integer seniority, String image, Double fixedSalary) {
        this.nationalId = nationalId;
        this.name = name;
        this.codeLicense = codeLicense;
        this.typeLicense = typeLicense;
        this.address = address;
        this.dob = dob;
        this.seniority = seniority;
        this.image = image;
        this.fixedSalary = fixedSalary;
    }

    public Integer getId() {
        return id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getName() {
        return name;
    }

    public String getCodeLicense() {
        return codeLicense;
    }

    public String getTypeLicense() {
        return typeLicense;
    }

    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public String getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodeLicense(String codeLicense) {
        this.codeLicense = codeLicense;
    }

    public void setTypeLicense(String typeLicense) {
        this.typeLicense = typeLicense;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(Double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
