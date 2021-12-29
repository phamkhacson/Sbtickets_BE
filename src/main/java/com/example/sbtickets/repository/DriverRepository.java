package com.example.sbtickets.repository;

import com.example.sbtickets.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
