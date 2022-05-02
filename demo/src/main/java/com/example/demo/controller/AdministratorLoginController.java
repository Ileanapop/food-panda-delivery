package com.example.demo.controller;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/administrator")
public class AdministratorLoginController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ResponseEntity<?> getCustomerByUsername(@Param("username") String username, @Param("password") String password){

        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            System.out.println(authInputToken);
            //authManager.authenticate(authInputToken);
            AdministratorDTO administrator = administratorService.loginAdministrator(username,password);
            if(administrator==null)
                return ResponseEntity.badRequest().body("Invalid Login Credentials");

            String token = jwtUtil.generateToken(username+"-admin");
            System.out.println(authInputToken);
            return new ResponseEntity<>(token,HttpStatus.OK);
        }catch (AuthenticationException authExc){
            return ResponseEntity.badRequest().body("Invalid Login Credentials");
        }

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
