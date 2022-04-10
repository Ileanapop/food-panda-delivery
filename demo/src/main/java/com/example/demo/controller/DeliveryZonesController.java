package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.service.DeliveryZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deliveryZones")
public class DeliveryZonesController {

    @Autowired
    private DeliveryZoneService deliveryZoneService;

    @GetMapping("/getZones")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getDeliveryZones() {

        List<ZoneDTO> zoneDTOS = deliveryZoneService.getDeliveryZones();
        List<String > zones = new ArrayList<>();
        for(ZoneDTO zoneDTO: zoneDTOS){
            zones.add(zoneDTO.getZoneName());
        }
        return zones;

    }

}
