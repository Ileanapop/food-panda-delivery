package com.example.demo.controller;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdministratorLoginController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/login/{username}")
    @ResponseStatus(HttpStatus.OK)
    public AdministratorDTO getCustomerByUsername(@PathVariable String username){

        System.out.println("Authentication started for" + username);
        //return administratorService.getAdministratorByUsername(username);
        return null;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public AdministratorDTO saveUser(@RequestBody AdministratorDTO administratorDTO){
        System.out.println("*********************");
        return administratorService.addAdministrator(administratorDTO);
    }

    @RequestMapping("/")
    public String index(){
        return "start";
    }
}
