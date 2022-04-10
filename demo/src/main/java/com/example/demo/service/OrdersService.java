package com.example.demo.service;


import com.example.demo.dto.AcceptedOrdersDTO;
import com.example.demo.dto.ViewOrderDTO;
import com.example.demo.model.Administrator;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.OrderStatusRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;


    public OrdersService(){

    }

    public List<ViewOrderDTO> getRestaurantOrders(String username){

        Optional<Administrator> administrator = administratorRepository.findByUsername(username);

        if(administrator.isPresent()){
            System.out.println(administrator.get().getUsername());
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByAdministratorId(administrator.get().getId());
            if(restaurant.isPresent()){
                System.out.println(restaurant.get().getName());
                List<ViewOrderDTO> viewOrderDTOS = new ArrayList<>();

                List<Order> orders = ordersRepository.findOrderByRestaurantIdRestaurant(restaurant.get().getIdRestaurant());
                System.out.println(orders);
                for(Order order: orders){
                    ViewOrderDTO viewOrderDTO = new ViewOrderDTO();
                    viewOrderDTO.setCustomer(order.getCustomer().getEmail());
                    viewOrderDTO.setId(order.getIdMenuItem());
                    viewOrderDTO.setStatus(order.getOrderStatus().getName());
                    viewOrderDTOS.add(viewOrderDTO);
                }
                return viewOrderDTOS;
            }
            return null;
        }
        return null;
    }

    public boolean acceptOrders(AcceptedOrdersDTO acceptedOrdersDTO){
        for(Integer orderId: acceptedOrdersDTO.getOrdersIds()){
            Order order = ordersRepository.getById(orderId);
            Optional<OrderStatus> orderStatus = orderStatusRepository.findByName("ACCEPTED");
            if(orderStatus.isPresent()){
                order.setOrderStatus(orderStatus.get());
                ordersRepository.save(order);
            }
        }
        return true;
    }

    public List<ViewOrderDTO> getRestaurantPendingOrders(String username){

        Optional<Administrator> administrator = administratorRepository.findByUsername(username);

        if(administrator.isPresent()){
            System.out.println(administrator.get().getUsername());
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByAdministratorId(administrator.get().getId());
            if(restaurant.isPresent()){
                System.out.println(restaurant.get().getName());
                List<ViewOrderDTO> viewOrderDTOS = new ArrayList<>();
                Optional<OrderStatus> pending = orderStatusRepository.findByName("PENDING");
                if(pending.isPresent()){
                    List<Order> orders = ordersRepository.findOrderByRestaurantIdRestaurantAndAndOrderStatus(restaurant.get().getIdRestaurant(),pending.get());
                    System.out.println(orders);
                    for(Order order: orders){
                        ViewOrderDTO viewOrderDTO = new ViewOrderDTO();
                        viewOrderDTO.setCustomer(order.getCustomer().getEmail());
                        viewOrderDTO.setId(order.getIdMenuItem());
                        viewOrderDTO.setStatus(order.getOrderStatus().getName());
                        viewOrderDTOS.add(viewOrderDTO);
                    }
                    return viewOrderDTOS;
                }


            }
            return null;
        }
        return null;
    }
}
