package com.example.demo.repository;


import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone,Integer> {

    Optional<DeliveryZone> findByZoneName(String zoneName);

}
