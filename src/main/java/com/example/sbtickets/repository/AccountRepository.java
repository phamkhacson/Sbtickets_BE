package com.example.sbtickets.repository;

import com.example.sbtickets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Integer> {
}
