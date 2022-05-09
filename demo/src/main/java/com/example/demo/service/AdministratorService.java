package com.example.demo.service;

import com.example.demo.controller.RestaurantController;
import com.example.demo.dto.AdministratorDTO;
import com.example.demo.model.Administrator;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.utils.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    private final static Logger LOGGER = Logger.getLogger(AdministratorService.class.getName());

    public AdministratorService(){
    }

    /**
     * Method for authenticating the administrator
     * @param username username of the admin who wants to login
     * @param password password of the admin
     * @return the administrator if the credentials sent were correct else returns null
     */
    public AdministratorDTO loginAdministrator(String username, String password){

        LOGGER.info("Verifying username");
        Optional<Administrator> administrator = administratorRepository.findByUsername(username);

        if(!administrator.isPresent()){
            return null;
        }

        LOGGER.info("Verifying password");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password,administrator.get().getPassword())){
            AdministratorMapper administratorMapper = new AdministratorMapper();
            return administratorMapper.convertToDTO(administrator.get());
        }
        LOGGER.warning("Incorrect credentials");
        return null;
    }

    /**
     * Method for registering a new admin
     * @param administratorDTO contains all data needed to create the new administrator
     * @return the administrator created
     */
    @Transactional
    public AdministratorDTO addAdministrator(AdministratorDTO administratorDTO){

        AdministratorMapper administratorMapper = new AdministratorMapper();
        Administrator newAdministrator = administratorMapper.convertFromDTO(administratorDTO);

        Optional<Administrator> administrator = administratorRepository.findByUsername(newAdministrator.getUsername());
        if(administrator.isPresent())
            return null;

        newAdministrator.setPassword(new BCryptPasswordEncoder().encode(newAdministrator.getPassword()));
        LOGGER.info("Saving new administrator");
        return administratorMapper.convertToDTO(administratorRepository.save(newAdministrator));
    }
}
