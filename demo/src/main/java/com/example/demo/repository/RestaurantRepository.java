package com.example.demo.repository;


import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> getRestaurantByAdministratorId(Integer administrator_id);
    Optional<Restaurant> getRestaurantByName(String name);
}
