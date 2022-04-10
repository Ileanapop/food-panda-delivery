package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.service.MenuItemService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/restaurantActions")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/addRestaurant")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PostMapping("/addFoods")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDTO addFood(@RequestBody MenuItemDTO menuItemDTO) {

        return menuItemService.addMenuItem(menuItemDTO);
    }

    @GetMapping("/viewMenuItems/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getMenuItemsByCategory(@PathVariable String category){

        return menuItemService.findMenuItemsByCategory(category);

    }

    @GetMapping("/viewAllMenuItems")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getAllMenuItems(@Param("username") String username){

        return menuItemService.getAllMenuItemsFromARestaurant(username);
    }

    @GetMapping("/viewRestaurantMenu")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getRestaurantMenu(@Param("name") String name){

        return menuItemService.getMenu(name);
    }

    @GetMapping("/getAllRestaurants")
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantDTO> getAllRestaurants(){

        return restaurantService.getAllRestaurants();

    }

}
