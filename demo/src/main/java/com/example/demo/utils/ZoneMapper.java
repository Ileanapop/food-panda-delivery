package com.example.demo.utils;

import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryZone;

public class ZoneMapper {
    public ZoneDTO convertToDTO(DeliveryZone deliveryZone){

        ZoneDTO zoneDTO = new ZoneDTO();

        zoneDTO.setIdZone(deliveryZone.getIdZone());
        zoneDTO.setZoneName(deliveryZone.getZoneName());

        return zoneDTO;
    }
}
