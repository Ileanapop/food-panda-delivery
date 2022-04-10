package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer> {

    List<Order> getOrderByRestaurant(Restaurant restaurant);
    List<Order> findOrderByRestaurantIdRestaurant(Integer IdRestaurant);
    List<Order> findOrderByRestaurantIdRestaurantAndAndOrderStatus(Integer IdRestaurant, OrderStatus orderStatus);
    List<Order> findOrderByCustomer_IdCustomer(Integer IdCustomer);
}
