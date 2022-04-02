package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer/login")
public class CustomerLoginController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByUsername(@PathVariable String username) {

        System.out.println("Authentication started for" + username);
        return customerService.getCustomerByUsername(username);
    }

    @PostMapping("/createAccount")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO saveUser(@RequestBody CustomerWrapperDTO customerWrapperDTO) {
        return customerService.addCustomer(customerWrapperDTO);
    }
}
