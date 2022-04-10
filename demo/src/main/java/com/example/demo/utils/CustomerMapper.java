package com.example.demo.utils;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Customer;

public class CustomerMapper {

    public Customer convertFromDTO(CustomerWrapperDTO customerWrapperDTO){

        return Customer.builder()
        .email(customerWrapperDTO.getCustomerDTO().getEmail())
        .firstName(customerWrapperDTO.getCustomerDTO().getFirstName())
        .lastName(customerWrapperDTO.getCustomerDTO().getLastName())
        .username(customerWrapperDTO.getLoginDTO().getUsername())
        .password(customerWrapperDTO.getLoginDTO().getPassword()).build();
    }

    public CustomerDTO convertToDTO(Customer customer){

        return CustomerDTO.builder()
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName()).build();

        /*CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName((customer.getLastName()));
        return customerDTO;*/
    }

    public LoginDTO convertLoginToDTO(Customer customer){

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword(customer.getPassword());
        loginDTO.setUsername(customer.getUsername());
        return loginDTO;
    }


}