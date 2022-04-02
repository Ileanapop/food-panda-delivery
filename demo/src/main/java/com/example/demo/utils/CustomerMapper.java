package com.example.demo.utils;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Customer;

public class CustomerMapper {

    public Customer convertFromDTO(CustomerWrapperDTO customerWrapperDTO){
        Customer customer = new Customer();

        customer.setEmail(customerWrapperDTO.getCustomerDTO().getEmail());
        customer.setFirstName(customerWrapperDTO.getCustomerDTO().getFirstName());
        customer.setLastName(customerWrapperDTO.getCustomerDTO().getLastName());
        customer.setUsername(customerWrapperDTO.getLoginDTO().getUsername());
        customer.setPassword(customerWrapperDTO.getLoginDTO().getPassword());
        return customer;
    }

    public CustomerDTO convertToDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName((customer.getLastName()));
        return customerDTO;
    }

    public LoginDTO convertLoginToDTO(Customer customer){

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword(customer.getPassword());
        loginDTO.setUsername(customer.getUsername());
        return loginDTO;
    }


}