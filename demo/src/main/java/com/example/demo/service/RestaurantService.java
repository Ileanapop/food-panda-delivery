package com.example.demo.service;


import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.Administrator;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.MenuItem;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.utils.MenuPDFExporter;
import com.example.demo.utils.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    private final static Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());


    public RestaurantService(){

    }

    /**
     * Method to add a new restaurant
     * @param restaurantDTO details needed to insert the restaurant
     * @return the created restaurant
     */
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO){

        RestaurantMapper restaurantMapper = new RestaurantMapper();
        Restaurant newRestaurant = restaurantMapper.convertFromDTO(restaurantDTO);

        List<DeliveryZone> deliveryZones = new ArrayList<>();

        System.out.println(restaurantDTO.getAdministrator().getUsername());
        LOGGER.info("Get administrator data");
        Optional<Administrator> administrator = administratorRepository.findByUsername(restaurantDTO.getAdministrator().getUsername());

        if(administrator.isPresent()){
            newRestaurant.setAdministrator(administrator.get());
            LOGGER.info("Verification of delivery zones");
            for(String zoneName : restaurantDTO.getDeliveryZones()){
                deliveryZones.add(deliveryZoneRepository.findByZoneName(zoneName).get());
            }
            newRestaurant.setDeliveryZones(deliveryZones);

            System.out.println(newRestaurant.getAdministrator());

            return restaurantMapper.convertToDTO(restaurantRepository.save(newRestaurant));
        }
        LOGGER.warning("Administrator does not exist");
       return null;

    }

    /**
     * Method to get all registered restaurants in the application
     * @return list of restaurants
     */
    public List<RestaurantDTO> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        LOGGER.info("Mapping the restaurants");
        for(Restaurant restaurant: restaurants)
            restaurantDTOS.add(restaurantMapper.convertToDTO(restaurant));

        return restaurantDTOS;
    }


    /**
     * Method to get list of menu items of a restaurant
     * @param username of the admin of the restaurant
     * @return list of menu items
     */
    public List<MenuItem> getListOfMenuItems(String username){
        LOGGER.info("Get administrator data");
        Optional<Administrator> administrator = administratorRepository.findByUsername(username);
        if(administrator.isPresent()){
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByAdministratorId(administrator.get().getId());
            if(restaurant.isPresent()){
                return menuItemRepository.findByRestaurant(restaurant.get());
            }
            return new ArrayList<>();
        }
        LOGGER.info("Admin does not exist, return empty list");
        return new ArrayList<>();
    }

}
