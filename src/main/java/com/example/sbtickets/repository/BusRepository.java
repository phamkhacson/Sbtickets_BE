package com.example.sbtickets.repository;
import com.example.sbtickets.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}