package com.example.demo.service;


import com.example.demo.dto.AcceptedOrdersDTO;
import com.example.demo.dto.ViewOrderDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
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

    @Autowired
    private CustomerRepository customerRepository;


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

    public List<ViewOrderDTO> filterOrdersByStatus(String status, String username){
        Optional<Administrator> administrator = administratorRepository.findByUsername(username);
        System.out.println("Start fileeeeeeeeeeeeeeeeeeeeeee");
        if(administrator.isPresent()){
            System.out.println(administrator.get().getUsername());
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByAdministratorId(administrator.get().getId());
            if(restaurant.isPresent()){
                System.out.println(restaurant.get().getName());
                List<ViewOrderDTO> viewOrderDTOS = new ArrayList<>();
                Optional<OrderStatus> orderStatus = orderStatusRepository.findByName(status);
                if(orderStatus.isPresent()){
                    List<Order> orders = ordersRepository.findOrderByRestaurantIdRestaurantAndAndOrderStatus(restaurant.get().getIdRestaurant(),orderStatus.get());
                    System.out.println(orders);
                    for(Order order: orders){
                        System.out.println(order.getOrderStatus().getName());
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

    public List<ViewOrderDTO> getCustomerOrders(String email){

        Optional<Customer> customer = customerRepository.findFirstByEmail(email);

        if(customer.isPresent()){
            List<Order> orders = ordersRepository.findOrderByCustomer_IdCustomer(customer.get().getIdCustomer());

            List<ViewOrderDTO> viewOrderDTOS = new ArrayList<>();

            for(Order order: orders){
                ViewOrderDTO viewOrderDTO = new ViewOrderDTO();
                viewOrderDTO.setStatus(order.getOrderStatus().getName());
                viewOrderDTO.setCustomer(customer.get().getEmail());
                viewOrderDTO.setId(order.getIdMenuItem());

                viewOrderDTOS.add(viewOrderDTO);
            }
            return viewOrderDTOS;
        }
        return null;
    }
}
