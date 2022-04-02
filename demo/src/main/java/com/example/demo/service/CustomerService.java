package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.utils.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(){

    }

    public CustomerDTO getCustomerByUsername(String username){
        return null;
    }

    @Transactional
    public CustomerDTO addCustomer(CustomerWrapperDTO customerWrapperDTO){
        CustomerMapper customerMapper = new CustomerMapper();
        Customer newCustomer = customerMapper.convertFromDTO(customerWrapperDTO);
        newCustomer.setPassword(new BCryptPasswordEncoder().encode(newCustomer.getPassword()));
        return customerMapper.convertToDTO(customerRepository.save(newCustomer));
    }

}
