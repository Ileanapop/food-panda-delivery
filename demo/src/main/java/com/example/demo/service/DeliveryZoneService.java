package com.example.demo.service;


import com.example.demo.dto.ZoneDTO;
import com.example.demo.model.DeliveryZone;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.utils.ZoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryZoneService {

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    public DeliveryZoneService(){

    }

    public List<ZoneDTO> getDeliveryZones(){
        List<DeliveryZone> deliveryZones = deliveryZoneRepository.findAll();
        List<ZoneDTO> zoneDTOS = new ArrayList<>();

        ZoneMapper zoneMapper = new ZoneMapper();

        for (DeliveryZone deliveryZone: deliveryZones)
            zoneDTOS.add(zoneMapper.convertToDTO(deliveryZone));
        return zoneDTOS;
    }

    public ZoneDTO getZoneByName(String name){
        Optional<DeliveryZone> deliveryZone = deliveryZoneRepository.findByZoneName(name);
        ZoneMapper zoneMapper = new ZoneMapper();
        return deliveryZone.map(zoneMapper::convertToDTO).orElse(null);
    }



}
