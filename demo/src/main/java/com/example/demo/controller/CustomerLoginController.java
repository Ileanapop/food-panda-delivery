package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerLoginController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createAccount")
    public ResponseEntity<?> saveUser(@Valid @RequestBody CustomerWrapperDTO customerWrapperDTO) {
        CustomerDTO result = customerService.addCustomer(customerWrapperDTO);
        if(result == null){
            return ResponseEntity.badRequest().body("Insert new customer cannot be performed");
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getCustomerByUsername(@Param("username") String username, @Param("password") String password) {

        CustomerDTO customerDTO = customerService.loginCustomer(username,password);
        if(customerDTO==null)
            return ResponseEntity.badRequest().body("Invalid Login");
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        boolean result = customerService.createOrder(orderDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
