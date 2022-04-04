package com.example.demo.repository;

import com.example.demo.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AdministratorRepository extends JpaRepository<Administrator, String > {

    Optional<Administrator> findByUsername(String username);

}
