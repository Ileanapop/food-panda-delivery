package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.service.MenuItemService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RestaurantDTO saveUser(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PostMapping("/addFoods")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> addFood(@RequestBody List<MenuItemDTO> menuItemDTOS) {

        List<MenuItemDTO> menuItemDTOList = new ArrayList<>();

        for(MenuItemDTO menuItemDTO: menuItemDTOS){
            menuItemDTOList.add(menuItemService.addMenuItem(menuItemDTO));
        }
        return menuItemDTOList;
    }

    @GetMapping("/viewMenuItems/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getMenuItemsByCategory(@PathVariable String category){

        return menuItemService.findMenuItemsByCategory(category);

    }

}
