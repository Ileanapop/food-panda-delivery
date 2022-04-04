package com.example.demo.controller;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/administrator/login")
public class AdministratorLoginController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public AdministratorDTO getCustomerByUsername(@PathVariable String username){

        System.out.println("Authentication started for" + username);
        //return administratorService.getAdministratorByUsername(username);
        return null;
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
