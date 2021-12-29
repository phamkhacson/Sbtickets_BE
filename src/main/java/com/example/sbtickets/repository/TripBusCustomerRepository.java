package com.example.sbtickets.repository;

import com.example.sbtickets.entity.TripBusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface TripBusCustomerRepository extends JpaRepository<TripBusCustomer, Integer> {
    @Query(
            "select u from TripBusCustomer u where u.tripbus.id = ?1"
    )
    public List<TripBusCustomer> getListByTripBusId(Integer id);


    @Transactional
    @Modifying
    @Query(
            "delete from TripBusCustomer u where u.tripbus.id = :id"
    )
    public void deleteTripBusCustomerById(Integer id);
}
