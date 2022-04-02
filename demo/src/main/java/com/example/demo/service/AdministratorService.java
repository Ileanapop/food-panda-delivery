package com.example.demo.service;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.utils.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorService(){
    }

    @Transactional
    public AdministratorDTO addAdministrator(AdministratorDTO administratorDTO){
        AdministratorMapper administratorMapper = new AdministratorMapper();
        return administratorMapper.convertToDTO(administratorRepository.save(administratorMapper.convertFromDTO(administratorDTO)));
    }
}
