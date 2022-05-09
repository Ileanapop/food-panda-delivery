package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.utils.AdministratorMapper;
import com.example.demo.utils.CustomerMapper;
import com.example.demo.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


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

    @Autowired
    private JavaMailSender javaMailSender;

    private final static Logger LOGGER = Logger.getLogger(CustomerService.class.getName());

    public CustomerService(){

    }

    /**
     * Method for authenticating the customer
     * @param username of the customer who want to log in
     * @param password of the customer
     * @return the customer data
     */
    public CustomerDTO loginCustomer(String username, String password){
        LOGGER.info("Verifying credentials");
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

    /**
     * Method for registering a new customer
     * @param customerWrapperDTO customer data needed to create the new customer
     * @return the created customer
     */
    @Transactional
    public CustomerDTO addCustomer(CustomerWrapperDTO customerWrapperDTO){

        CustomerMapper customerMapper = new CustomerMapper();
        Customer newCustomer = customerMapper.convertFromDTO(customerWrapperDTO);

        LOGGER.info("Verifying duplicated username");
        Optional<Customer> existingCustomer = customerRepository.findFirstByUsername(newCustomer.getUsername());
        if(existingCustomer.isPresent())
            return null;

        LOGGER.info("Verifying duplicated email");
        Optional<Customer> existingCustomerWithEmailAddress = customerRepository.findFirstByEmail(newCustomer.getEmail());
        if(existingCustomerWithEmailAddress.isPresent()){
            return null;
        }

        newCustomer.setPassword(new BCryptPasswordEncoder().encode(newCustomer.getPassword()));
        return customerMapper.convertToDTO(customerRepository.save(newCustomer));
    }

    public boolean createOrder(OrderDTO orderDTO){

        LOGGER.info("Identifying restaurant");
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
                    double totalPrice = 0;
                    for(String menuItemName: orderDTO.getFoods()){
                        Optional<MenuItem> orderedItem = menuItemRepository.findByItemNameAndRestaurant(menuItemName,restaurant.get());
                        orderedItem.ifPresent(menuItems::add);
                        if(orderedItem.isPresent()){
                            totalPrice+=orderedItem.get().getPrice();
                        }
                    }
                    order.setOrderMenuItems(menuItems);
                    order.setCustomer(customer.get());
                    order.setOrderStatus(orderStatus.get());
                    order.setRestaurant(restaurant.get());
                    ordersRepository.save(order);

                    LOGGER.info("Generate mail body");
                    MailContentService mailContentService = new MailContentService(menuItems,totalPrice,orderDTO.getSpecialDetails(),orderDTO.getAddress());
                    String mailContent = mailContentService.generateReportMessage();

                    LOGGER.info("Sending mail");
                    Administrator administrator = restaurant.get().getAdministrator();
                    MailSender mailSender = new MailSender("lunadog0607@gmail.com", customer.get().getEmail());

                    mailSender.send(mailContent,javaMailSender);

                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

}
