package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.utils.AdministratorMapper;
import com.example.demo.utils.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public CustomerService(){

    }

    public CustomerDTO loginCustomer(String username, String password){
        Optional<Customer> customer = customerRepository.findFirstByUsername(username);
        if(!customer.isPresent()){
            return null;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password,customer.get().getPassword())){
            CustomerMapper customerMapper = new CustomerMapper();
            return customerMapper.convertToDTO(customer.get());
        }
        return null;
    }

    @Transactional
    public CustomerDTO addCustomer(CustomerWrapperDTO customerWrapperDTO){
        CustomerMapper customerMapper = new CustomerMapper();
        Customer newCustomer = customerMapper.convertFromDTO(customerWrapperDTO);

        Optional<Customer> existingCustomer = customerRepository.findFirstByUsername(newCustomer.getUsername());
        if(existingCustomer.isPresent())
            return null;

        Optional<Customer> existingCustomerWithEmailAddress = customerRepository.findFirstByEmail(newCustomer.getEmail());
        if(existingCustomerWithEmailAddress.isPresent()){
            return null;
        }

        newCustomer.setPassword(new BCryptPasswordEncoder().encode(newCustomer.getPassword()));
        return customerMapper.convertToDTO(customerRepository.save(newCustomer));
    }

    public boolean createOrder(OrderDTO orderDTO){

        Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByName(orderDTO.getRestaurant());
        System.out.println(orderDTO.getRestaurant());
        System.out.println(orderDTO.getCustomer());
        if(restaurant.isPresent()){
            Optional<Customer> customer = customerRepository.findFirstByEmail(orderDTO.getCustomer());
            if(customer.isPresent()){
                Optional<OrderStatus> orderStatus = orderStatusRepository.findByName("PENDING");
                if(orderStatus.isPresent()){
                    Order order = new Order();
                    List<MenuItem> menuItems = new ArrayList<>();

                    for(String menuItemName: orderDTO.getFoods()){
                        Optional<MenuItem> orderedItem = menuItemRepository.findByItemNameAndRestaurant(menuItemName,restaurant.get());
                        orderedItem.ifPresent(menuItems::add);

                    }
                    order.setOrderMenuItems(menuItems);
                    order.setCustomer(customer.get());
                    order.setOrderStatus(orderStatus.get());
                    order.setRestaurant(restaurant.get());
                    ordersRepository.save(order);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

}
