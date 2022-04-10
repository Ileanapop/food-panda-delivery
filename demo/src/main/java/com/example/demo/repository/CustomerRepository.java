package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByUsername(String  username);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findFirstByUsername(String username);
    Optional<Customer> findFirstByEmail(String email);
}
