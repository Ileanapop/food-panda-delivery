package com.example.demo.service.security;


import com.example.demo.model.Administrator;
import com.example.demo.model.Customer;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class AdminDetailService implements UserDetailsService {

    private AdministratorRepository administratorRepository;

    @Autowired
    public AdminDetailService(AdministratorRepository administratorRepository){
        this.administratorRepository = administratorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Administrator> administrator = administratorRepository.findByUsername(username);

        if(!administrator.isPresent())
            throw new UsernameNotFoundException("Could not find admin with username = " + username);
        Administrator identifiedAdministrator = administrator.get();
        //System.out.println(administrator.get().getUsername());
        return new org.springframework.security.core.userdetails.User(
                username,
                identifiedAdministrator.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
