package com.academy.TransDana.model.repository;

import com.academy.TransDana.model.domain.Route;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    Page<Route> findByArrivaldateBetween(Date arrivalDate, Date departureDate, Pageable pageable);

    List<Route> findAllByUser_id(Integer id);

    Page<Route> findByUser_idAndArrivaldateBetween(Integer id, Date arrivalDate, Date departureDate, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Route r SET r.status = :status WHERE r.id = :id")
    void updateRouteStatus(Integer id, String status);

}
