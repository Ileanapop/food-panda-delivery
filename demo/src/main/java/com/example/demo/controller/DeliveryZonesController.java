package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.service.DeliveryZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/deliveryZones")
public class DeliveryZonesController {

    @Autowired
    private DeliveryZoneService deliveryZoneService;

    private final static Logger LOGGER = Logger.getLogger(DeliveryZonesController.class.getName());

    @GetMapping("/getZones")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getDeliveryZones() {

        LOGGER.info("Start request to get delivery zones");
        List<ZoneDTO> zoneDTOS = deliveryZoneService.getDeliveryZones();
        List<String > zones = new ArrayList<>();
        for(ZoneDTO zoneDTO: zoneDTOS){
            zones.add(zoneDTO.getZoneName());
        }
        LOGGER.info("Finish request to get delivery zones");
        return zones;

    }

}
