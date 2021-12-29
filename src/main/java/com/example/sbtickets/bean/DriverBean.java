package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DriverBean {
    private Integer id;
    private Integer salaryId;
    private String nationalId;
    private String name;
    private String codeLicense;
    private String typeLicense;
    private String address;
    private Date dob;
    private Integer seniority;
    private String image;
    private Double fixedSalary;
}
