package com.example.demo.service;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.model.Administrator;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.utils.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorService(){
    }

    @Transactional
    public AdministratorDTO addAdministrator(AdministratorDTO administratorDTO){
        AdministratorMapper administratorMapper = new AdministratorMapper();
        Administrator newAdministrator = administratorMapper.convertFromDTO(administratorDTO);

        Optional<Administrator> administrator = administratorRepository.findByUsername(newAdministrator.getUsername());
        if(administrator.isPresent())
            return null;

        newAdministrator.setPassword(new BCryptPasswordEncoder().encode(newAdministrator.getPassword()));
        return administratorMapper.convertToDTO(administratorRepository.save(newAdministrator));
    }
}
