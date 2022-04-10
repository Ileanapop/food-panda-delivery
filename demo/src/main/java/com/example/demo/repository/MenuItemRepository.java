package com.example.demo.repository;


import com.example.demo.model.MenuItem;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {

    @Modifying
    @Transactional
    @Query("select m from MenuItem m LEFT JOIN FoodCategory f ON m.category = f WHERE f.name = ?1")
    List<MenuItem> findMenuItemsByCategory(String name);

    List<MenuItem> findByRestaurant(Restaurant restaurant);

    Optional<MenuItem> findByItemNameAndRestaurant(String itemName, Restaurant restaurant);
}
