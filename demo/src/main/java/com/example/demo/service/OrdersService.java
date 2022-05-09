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
import java.util.logging.Logger;

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

    private final static Logger LOGGER = Logger.getLogger(OrdersService.class.getName());


    public OrdersService(){

    }


    /**
     * Method to get the orders of the restaurant
     * @param username of the administrator
     * @return the list of all restaurant orders
     */
    public List<ViewOrderDTO> getRestaurantOrders(String username){

        Optional<Administrator> administrator = administratorRepository.findByUsername(username);


        if(administrator.isPresent()){
            LOGGER.info("Administrator identified");
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

    /**
     * Method to accept order
     * @param acceptedOrdersDTO data consisting list of orders to be accepted by admin
     * @return true if order status changed to accepted
     */
    public boolean acceptOrders(AcceptedOrdersDTO acceptedOrdersDTO){
        for(Integer orderId: acceptedOrdersDTO.getOrdersIds()){
            Order order = ordersRepository.getById(orderId);
            Optional<OrderStatus> orderStatus = orderStatusRepository.findByName("ACCEPTED");
            if(orderStatus.isPresent()){
                order.setOrderStatus(orderStatus.get());
                ordersRepository.save(order);
            }
        }
        LOGGER.info("Order accepted");
        return true;
    }


    /**
     * Method to get restaurant pending orders
     * @param username of the admin of the restaurant
     * @return list of pending orders
     */
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


    /**
     * Method to filter the orders by status
     * @param status order status
     * @param username of the admin of the restaurant
     * @return list of filtered orders
     */
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


    /**
     * Method to get customer orders
     * @param email of the customer
     * @return list of orders of the customer
     */
    public List<ViewOrderDTO> getCustomerOrders(String email){

        Optional<Customer> customer = customerRepository.findFirstByEmail(email);

        if(customer.isPresent()){
            LOGGER.info("Customer identified");
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
