package com.example.demo;


import com.example.demo.dto.AdministratorDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.ViewOrderDTO;
import com.example.demo.model.Administrator;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.*;
import com.example.demo.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {


    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private DeliveryZoneRepository deliveryZoneRepository;

    @Mock
    private AdministratorRepository administratorRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addRestaurantNonExistingAdministratorShouldReturnNull() {

        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        AdministratorDTO administratorDTO = new AdministratorDTO();
        administratorDTO.setPassword("password");
        administratorDTO.setUsername("username");

        restaurantDTO.setLocation("location");
        restaurantDTO.setName("name");
        restaurantDTO.setAdministrator(administratorDTO);

        RestaurantDTO restaurant = restaurantService.addRestaurant(restaurantDTO);

        Assertions.assertNull(restaurant);
    }

    @Test
    public void addRestaurantCorrectDataShouldReturnNewRestaurant() {

        Administrator administrator = new Administrator();
        administrator.setPassword("password");
        administrator.setUsername("username");
        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(administrator));

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        AdministratorDTO administratorDTO = new AdministratorDTO();
        administratorDTO.setPassword("password");
        administratorDTO.setUsername("username");

        restaurantDTO.setLocation("location");
        restaurantDTO.setName("name");
        restaurantDTO.setAdministrator(administratorDTO);

        DeliveryZone deliveryZone =new DeliveryZone();
        deliveryZone.setZoneName("zone");
        List<String> zones = new ArrayList<>();
        List<DeliveryZone> deliveryZones = new ArrayList<>();
        deliveryZones.add(deliveryZone);
        zones.add(deliveryZone.getZoneName());
        restaurantDTO.setDeliveryZones(zones);

        DeliveryZone deliveryZone1 = new DeliveryZone();
        deliveryZone1.setZoneName("zone_name");

        Restaurant returnedRestaurant = new Restaurant();
        returnedRestaurant.setAdministrator(administrator);
        returnedRestaurant.setLocation("location");
        returnedRestaurant.setName("restaurant");
        returnedRestaurant.setDeliveryZones(deliveryZones);

        Mockito.when(deliveryZoneRepository.findByZoneName(Mockito.anyString())).thenReturn(Optional.of(deliveryZone1));
        Mockito.when(restaurantRepository.save(Mockito.any())).thenReturn(returnedRestaurant);

        RestaurantDTO restaurant = restaurantService.addRestaurant(restaurantDTO);

        Assertions.assertNotNull(restaurant);
        Assertions.assertEquals("location",restaurant.getLocation());
        Assertions.assertEquals("restaurant",restaurant.getName());
    }

}
