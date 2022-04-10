package com.example.demo.repository;

import com.example.demo.model.FoodCategory;
import com.example.demo.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {

    Optional<FoodCategory> findByName(String name);

}
