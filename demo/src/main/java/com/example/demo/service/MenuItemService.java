package com.example.demo.service;


import com.example.demo.dto.MenuItemDTO;
import com.example.demo.model.Administrator;
import com.example.demo.model.FoodCategory;
import com.example.demo.model.MenuItem;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.FoodCategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.utils.MenuItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    private final static Logger LOGGER = Logger.getLogger(MenuItemService.class.getName());

    public MenuItemService(){

    }


    /**
     * Method to add the a new menu item
     * @param menuItemDTO data containing properties of the new item
     * @return the created menu item
     */
    public MenuItemDTO addMenuItem(MenuItemDTO menuItemDTO){

        MenuItemMapper menuItemMapper = new MenuItemMapper();
        MenuItem newMenuItem = menuItemMapper.convertFromDTO(menuItemDTO);

        LOGGER.info("Verifying category of menu item");
        Optional<FoodCategory> foodCategory = foodCategoryRepository.findByName(menuItemDTO.getCategory());
        if(foodCategory.isPresent()){
            newMenuItem.setCategory(foodCategory.get());
        }
        else
            return null;

        LOGGER.info("Getting the restaurant");
        Optional<Administrator> administrator = administratorRepository.findByUsername(menuItemDTO.getAdministrator());

        if(administrator.isPresent()){
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByAdministratorId(administrator.get().getId());
            if(restaurant.isPresent()){
                newMenuItem.setRestaurant(restaurant.get());
            }
            else
                return null;
        }

        return menuItemMapper.convertToDTO(menuItemRepository.save(newMenuItem));
    }

    /**
     * Method to search the menu items by category
     * @param category of the menu items
     * @return list of the menu items of category
     */
    public List<MenuItemDTO> findMenuItemsByCategory(String category){
        List<MenuItemDTO> menuItemDTOList = new ArrayList<>();
        List<MenuItem> menuItems = menuItemRepository.findMenuItemsByCategory(category);

        MenuItemMapper menuItemMapper = new MenuItemMapper();

        LOGGER.info("Mapping menu items");
        for(MenuItem menuItem: menuItems){
            menuItemDTOList.add(menuItemMapper.convertToDTO(menuItem));
        }
        return menuItemDTOList;
    }

    /**
     * Method to get all menu items of a restaurant
     * @param administratorUsername username of the admin of the restaurant
     * @return list of menu items of the restaurant
     */
    public List<MenuItemDTO> getAllMenuItemsFromARestaurant(String administratorUsername){
        Optional<Administrator> administrator = administratorRepository.findByUsername(administratorUsername);
        if(administrator.isPresent()){
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByAdministratorId(administrator.get().getId());
            if(restaurant.isPresent()){
                List<MenuItem> menuItems = menuItemRepository.findByRestaurant(restaurant.get());

                MenuItemMapper menuItemMapper = new MenuItemMapper();

                List<MenuItemDTO> menuItemDTOList = new ArrayList<>();
                for(MenuItem menuItem:menuItems){
                    MenuItemDTO menuItemDTO = menuItemMapper.convertToDTO(menuItem);
                    menuItemDTO.setCategory(menuItem.getCategory().getName());
                    menuItemDTOList.add(menuItemDTO);
                }

                return menuItemDTOList;
            }

        }
        return null;
    }

    /**
     * Method to get the menu of a restaurant
     * @param name of the restaurant
     * @return list of menu items
     */
    public List<MenuItemDTO> getMenu(String name){

        LOGGER.info("Identify the restaurant");
            Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByName(name);
            if(restaurant.isPresent()){
                List<MenuItem> menuItems = menuItemRepository.findByRestaurant(restaurant.get());

                MenuItemMapper menuItemMapper = new MenuItemMapper();

                List<MenuItemDTO> menuItemDTOList = new ArrayList<>();
                for(MenuItem menuItem:menuItems){
                    MenuItemDTO menuItemDTO = menuItemMapper.convertToDTO(menuItem);
                    menuItemDTO.setCategory(menuItem.getCategory().getName());
                    menuItemDTOList.add(menuItemDTO);
                }

                return menuItemDTOList;
            }
        LOGGER.warning("The restaurant does not exist");
        return null;
    }
}
