package com.example.demo.service;


import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.Administrator;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.utils.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    @Autowired
    private AdministratorRepository administratorRepository;


    public RestaurantService(){

    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO){

        RestaurantMapper restaurantMapper = new RestaurantMapper();
        Restaurant newRestaurant = restaurantMapper.convertFromDTO(restaurantDTO);

        List<DeliveryZone> deliveryZones = new ArrayList<>();

        System.out.println(restaurantDTO.getAdministrator().getUsername());
        Optional<Administrator> administrator = administratorRepository.findByUsername(restaurantDTO.getAdministrator().getUsername());

        if(administrator.isPresent()){
            newRestaurant.setAdministrator(administrator.get());

            for(String zoneName : restaurantDTO.getDeliveryZones()){
                deliveryZones.add(deliveryZoneRepository.findByZoneName(zoneName).get());
            }
            newRestaurant.setDeliveryZones(deliveryZones);

            System.out.println(newRestaurant.getAdministrator());

            return restaurantMapper.convertToDTO(restaurantRepository.save(newRestaurant));
        }

       return null;

    }

    public List<RestaurantDTO> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        for(Restaurant restaurant: restaurants)
            restaurantDTOS.add(restaurantMapper.convertToDTO(restaurant));

        return restaurantDTOS;
    }

}
