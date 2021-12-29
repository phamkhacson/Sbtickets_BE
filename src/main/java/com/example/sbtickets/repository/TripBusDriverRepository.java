package com.example.sbtickets.repository;

import com.example.sbtickets.entity.TripBusDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


public interface TripBusDriverRepository extends JpaRepository<TripBusDriver, Integer> {
    @Transactional
    @Modifying
    @Query(
            "delete from TripBusDriver u where u.tripbus.id = ?1"
    )
    public void deleteTripBusDriver(Integer tripBusId);

    @Query(
            "select u from TripBusDriver u where u.driver.id = :driverId and u.scrapDateTime >= :scrapTimeFrom and u.scrapDateTime <= :scrapTimeTo"
    )
    public List<TripBusDriver> listByDriverId(Integer driverId, String scrapTimeFrom, String scrapTimeTo);


    @Transactional
    @Modifying
    @Query(
            "update TripBusDriver as u SET u.date = :date, u.wages = :wages , u.scrapDateTime = :scrapDateTime, u.driver.id = :driverId where u.tripbus.id = :tripBusId and u.roleCar = :roleCar "
    )
    public void editTripBusDriver(Date date, Double wages, String scrapDateTime, Integer driverId, Integer tripBusId, String roleCar);
}
