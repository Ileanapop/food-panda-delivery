package com.example.demo.utils;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.CustomerWrapperDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.Restaurant;

public class RestaurantMapper {

    public Restaurant convertFromDTO(RestaurantDTO restaurantDTO){

        Restaurant restaurant = new Restaurant();
        restaurant.setAdministrator(restaurantDTO.getAdministrator());
        restaurant.setDeliveryZones(restaurantDTO.getDeliveryZones());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setName(restaurantDTO.getName());
        return restaurant;

    }

    public RestaurantDTO convertToDTO(Restaurant restaurant){

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAdministrator(restaurant.getAdministrator());
        restaurantDTO.setDeliveryZones(restaurant.getDeliveryZones());
        restaurantDTO.setLocation(restaurant.getLocation());
        restaurantDTO.setName(restaurant.getName());
        return restaurantDTO;

    }

}
