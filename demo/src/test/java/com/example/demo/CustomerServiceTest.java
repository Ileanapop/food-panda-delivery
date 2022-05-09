package com.example.demo;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Customer;
import com.example.demo.repository.*;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.CustomerMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    @Mock
    private BCryptPasswordEncoder encoder;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void loginCustomerCorrectCredentialsShouldReturnCustomer(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode("password");
        Mockito.when(customerRepository.findFirstByUsername(Mockito.anyString())).thenReturn(java.util.Optional.of(new Customer("username", encodedPassword, "firstname", "lastname", "email")));

        String username = "username";
        String password = "password";

        CustomerDTO customer = customerService.loginCustomer(username,password);
        Assertions.assertEquals("email", customer.getEmail());
        Assertions.assertEquals("firstname", customer.getFirstName());
        Assertions.assertEquals("lastname", customer.getLastName());

    }

    @Test
    public void loginCustomerIncorrectCredentialsShouldReturnNull(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode("password");
        Mockito.when(customerRepository.findFirstByUsername(Mockito.anyString())).thenReturn(java.util.Optional.of(new Customer("username", encodedPassword, "firstname", "lastname", "email")));

        String username = "username";
        String password = "wrong";

        CustomerDTO customer = customerService.loginCustomer(username,password);
        Assertions.assertNull(customer);

    }

    @Test
    public void loginCustomerNonExistingUsernameShouldReturnNull(){

        Mockito.when(customerRepository.findFirstByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        String username = "wrong";
        String password = "wrong";

        CustomerDTO customer = customerService.loginCustomer(username,password);
        Assertions.assertNull(customer);

    }

    @Test
    public void addCustomerExistingUsernameShouldReturnNull(){

        Mockito.when(customerRepository.findFirstByUsername(Mockito.anyString())).thenReturn(Optional.of(new Customer()));
        CustomerDTO customerDTO = new CustomerDTO("firstname","lastname","email");
        LoginDTO loginDTO = new LoginDTO("existing_username","password");
        CustomerDTO customer = customerService.addCustomer(new CustomerWrapperDTO(loginDTO,customerDTO));

        Assertions.assertNull(customer);

    }

    @Test
    public void addCustomerExistingEmailShouldReturnNull(){

        Mockito.when(customerRepository.findFirstByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.findFirstByEmail(Mockito.anyString())).thenReturn(Optional.of(new Customer()));

        CustomerDTO customerDTO = new CustomerDTO("firstname","lastname","existing_email");
        LoginDTO loginDTO = new LoginDTO("username","password");
        CustomerDTO customer = customerService.addCustomer(new CustomerWrapperDTO(loginDTO,customerDTO));

        Assertions.assertNull(customer);

    }

    @Test
    public void addCustomerCorrectDataShouldReturnAddedCustomerDTO(){

        Mockito.when(customerRepository.findFirstByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.findFirstByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("encoded");

        CustomerDTO customerDTO = new CustomerDTO("firstname","lastname","email");
        LoginDTO loginDTO = new LoginDTO("username","password");

        String encodedPassword = encoder.encode("password");
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(new Customer("username", encodedPassword, "firstname", "lastname", "email"));

        CustomerDTO customer = customerService.addCustomer(new CustomerWrapperDTO(loginDTO,customerDTO));

        Assertions.assertEquals("email", customer.getEmail());
        Assertions.assertEquals("firstname", customer.getFirstName());
        Assertions.assertEquals("lastname", customer.getLastName());

    }

}
