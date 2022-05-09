package com.example.demo;


import com.example.demo.dto.AdministratorDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.model.Administrator;
import com.example.demo.model.DeliveryZone;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.DeliveryZoneService;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryZonesServiceTest {

    @InjectMocks
    private DeliveryZoneService deliveryZoneService;

    @Mock
    private DeliveryZoneRepository deliveryZoneRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDeliveryZonesShouldReturnListDeliveryZones(){

        List<DeliveryZone> deliveryZones = new ArrayList<>();
        DeliveryZone zone1 = new DeliveryZone();
        zone1.setIdZone(1);
        zone1.setZoneName("Name1");

        DeliveryZone zone2 = new DeliveryZone();
        zone2.setIdZone(2);
        zone2.setZoneName("Name2");

        deliveryZones.add(zone1);
        deliveryZones.add(zone2);

        Mockito.when(deliveryZoneRepository.findAll()).thenReturn(deliveryZones);

        List<ZoneDTO> allZones = deliveryZoneService.getDeliveryZones();

        Assertions.assertEquals(deliveryZones.size(),allZones.size());
        Assertions.assertEquals(1,allZones.get(0).getIdZone());
        Assertions.assertEquals(2,allZones.get(1).getIdZone());
        Assertions.assertEquals("Name1",allZones.get(0).getZoneName());
        Assertions.assertEquals("Name2",allZones.get(1).getZoneName());
    }

}
