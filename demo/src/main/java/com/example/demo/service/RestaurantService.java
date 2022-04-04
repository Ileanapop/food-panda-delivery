package com.example.demo.service;


import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.utils.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    public RestaurantService(){

    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO){

        RestaurantMapper restaurantMapper = new RestaurantMapper();
        Restaurant newRestaurant = restaurantMapper.convertFromDTO(restaurantDTO);
        return restaurantMapper.convertToDTO(restaurantRepository.save(newRestaurant));

    }

}
