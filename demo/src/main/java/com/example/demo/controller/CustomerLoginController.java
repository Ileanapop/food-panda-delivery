package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> saveUser(@Valid @RequestBody CustomerWrapperDTO customerWrapperDTO) {
        CustomerDTO result = customerService.addCustomer(customerWrapperDTO);
        if(result == null){
            return ResponseEntity.badRequest().body("Insert new customer cannot be performed");
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
