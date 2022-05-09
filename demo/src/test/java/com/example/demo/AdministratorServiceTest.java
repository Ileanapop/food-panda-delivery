package com.example.demo;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.model.Administrator;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.service.AdministratorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AdministratorServiceTest {

    @InjectMocks
    private AdministratorService administratorService;

    @Mock
    private AdministratorRepository administratorRepository;

    @Mock
    private BCryptPasswordEncoder encoder;


    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loginAdministratorCorrectCredentialsShouldReturnAdministrator(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode("password");
        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.of(new Administrator("username", encodedPassword)));

        String username = "username";
        String password = "password";

        AdministratorDTO administrator = administratorService.loginAdministrator(username,password);
        Assertions.assertEquals("username", administrator.getUsername());
        Assertions.assertEquals(encodedPassword, administrator.getPassword());

    }

    @Test
    public void loginAdministratorIncorrectCredentialsShouldReturnNull(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode("password");
        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.of(new Administrator("username", encodedPassword)));

        String username = "username";
        String password = "wrong";

        AdministratorDTO administratorDTO = administratorService.loginAdministrator(username,password);
        Assertions.assertNull(administratorDTO);

    }

    @Test
    public void loginCustomerNonExistingUsernameShouldReturnNull(){

        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        String username = "wrong";
        String password = "wrong";

        AdministratorDTO administratorDTO = administratorService.loginAdministrator(username,password);
        Assertions.assertNull(administratorDTO);

    }

    @Test
    public void addAdministratorExistingUsernameShouldReturnNull(){

        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(new Administrator()));
        AdministratorDTO administratorDTO = new AdministratorDTO("username","password");

        AdministratorDTO administrator = administratorService.addAdministrator(administratorDTO);

        Assertions.assertNull(administrator);

    }


    @Test
    public void addAdministratorCorrectDataShouldReturnAddedAdministratorDTO(){

        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("encoded");

        AdministratorDTO administratorDTO = new AdministratorDTO("username","password");

        String encodedPassword = encoder.encode("password");
        Mockito.when(administratorRepository.save(Mockito.any())).thenReturn(new Administrator("username", encodedPassword));

        AdministratorDTO administrator = administratorService.addAdministrator(administratorDTO);

        Assertions.assertEquals("username", administrator.getUsername());
        Assertions.assertEquals(encodedPassword, administrator.getPassword());

    }
}
