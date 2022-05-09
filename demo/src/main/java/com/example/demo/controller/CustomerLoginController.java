package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/customer")
public class CustomerLoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    private final static Logger LOGGER = Logger.getLogger(CustomerLoginController.class.getName());

    @PostMapping("/createAccount")
    public ResponseEntity<?> saveUser(@Valid @RequestBody CustomerWrapperDTO customerWrapperDTO) {
        LOGGER.info("Register operation started");
        CustomerDTO result = customerService.addCustomer(customerWrapperDTO);
        if(result == null){
            LOGGER.warning("Register cannot be performed");
            return ResponseEntity.badRequest().body("Insert new customer cannot be performed");
        }
        LOGGER.warning("Register finished successfully");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getCustomerByUsername(@Param("username") String username, @Param("password") String password) {

        LOGGER.info("Customer Authentication started");
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            System.out.println(authInputToken);
            //authManager.authenticate(authInputToken);
            CustomerDTO customerDTO = customerService.loginCustomer(username,password);

            if(customerDTO==null)
                return ResponseEntity.badRequest().body("Invalid Login Credentials");

            String token = jwtUtil.generateToken(username+"-customer");
            System.out.println(authInputToken);
            TokenDTO tokenDTO = new TokenDTO(token,customerDTO.getEmail());
            return new ResponseEntity<>(tokenDTO,HttpStatus.OK);
        }catch (AuthenticationException authExc){
            return ResponseEntity.badRequest().body("Invalid Login Credentials");
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        boolean result = customerService.createOrder(orderDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
