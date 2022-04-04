package com.example.demo.repository;


import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
