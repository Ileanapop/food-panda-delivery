package com.example.demo.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthorizeController {

    @GetMapping("/admin")
    public ResponseEntity<?> authorizeAdmin() {
        return new ResponseEntity<>("Granted Access", HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<?> authorizeCustomer() {
        return new ResponseEntity<>("Granted Access", HttpStatus.OK);
    }

}
