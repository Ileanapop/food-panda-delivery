package com.example.demo.controller;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/administrator")
public class AdministratorLoginController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/login")
    public ResponseEntity<?> getCustomerByUsername(@Param("username") String username, @Param("password") String password){

        System.out.println("Authentication started for" + username);
        AdministratorDTO administrator = administratorService.loginAdministrator(username,password);
        if(administrator==null)
            return ResponseEntity.badRequest().body("Invalid Login");
        return new ResponseEntity<>(administrator,HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> saveUser(@Valid @RequestBody AdministratorDTO administratorDTO){
        AdministratorDTO newAdministrator = administratorService.addAdministrator(administratorDTO);
        if(newAdministrator==null)
            return ResponseEntity.badRequest().body("Insert cannot be performed");
        return new ResponseEntity<>(newAdministrator,HttpStatus.OK);
    }

    @RequestMapping("/")
    public String index(){
        return "start";
    }
}
