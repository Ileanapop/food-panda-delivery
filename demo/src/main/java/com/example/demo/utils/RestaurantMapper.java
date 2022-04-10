package com.example.demo.utils;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;
import com.example.demo.service.DeliveryZoneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {

    public Restaurant convertFromDTO(RestaurantDTO restaurantDTO){

        Restaurant restaurant = new Restaurant();
        AdministratorMapper administratorMapper = new AdministratorMapper();
        restaurant.setAdministrator(administratorMapper.convertFromDTO(restaurantDTO.getAdministrator()));
       // restaurant.setDeliveryZones(restaurantDTO.getDeliveryZones());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setName(restaurantDTO.getName());
        return restaurant;

    }

    public RestaurantDTO convertToDTO(Restaurant restaurant){

        AdministratorMapper administratorMapper = new AdministratorMapper();

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAdministrator(administratorMapper.convertToDTO(restaurant.getAdministrator()));

        List<String> deliveryZonesNames = new ArrayList<>();
        for(DeliveryZone deliveryZone:restaurant.getDeliveryZones())
            deliveryZonesNames.add(deliveryZone.getZoneName());

        restaurantDTO.setDeliveryZones(deliveryZonesNames);

        restaurantDTO.setLocation(restaurant.getLocation());
        restaurantDTO.setName(restaurant.getName());
        return restaurantDTO;

    }

}
