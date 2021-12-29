package com.example.sbtickets.repository;

import com.example.sbtickets.entity.LineBus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineBusRepository extends JpaRepository<LineBus, Integer> {
}