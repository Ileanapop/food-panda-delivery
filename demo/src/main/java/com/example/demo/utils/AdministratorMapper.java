package com.example.demo.utils;

import com.example.demo.dto.AdministratorDTO;
import com.example.demo.model.Administrator;

public class AdministratorMapper {

    public Administrator convertFromDTO(AdministratorDTO administratorDTO){
        Administrator administrator = new Administrator();

        administrator.setUsername(administratorDTO.getUsername());
        administrator.setPassword(administratorDTO.getPassword());
        return administrator;
    }

    public AdministratorDTO convertToDTO(Administrator administrator){

        AdministratorDTO administratorDTO = new AdministratorDTO();

        administratorDTO.setUsername(administrator.getUsername());
        administratorDTO.setPassword(administrator.getPassword());
        return administratorDTO;
    }

}
