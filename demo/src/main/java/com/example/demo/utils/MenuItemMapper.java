package com.example.demo.utils;

import com.example.demo.dto.MenuItemDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.MenuItem;
import com.example.demo.model.Restaurant;

public class MenuItemMapper {

    public MenuItem convertFromDTO(MenuItemDTO menuItemDTO){

       MenuItem menuItem = new MenuItem();

       menuItem.setItemName(menuItemDTO.getItemName());
       //menuItem.setCategory(menuItemDTO.getCategory());
       menuItem.setDescription(menuItemDTO.getDescription());
       menuItem.setPrice(Double.parseDouble(menuItemDTO.getPrice()));
       return menuItem;

    }

    public MenuItemDTO convertToDTO(MenuItem menuItem){

        MenuItemDTO menuItemDTO = new MenuItemDTO();

        menuItemDTO.setItemName(menuItem.getItemName());
        //menuItemDTO.setCategory(menuItem.getCategory());
        menuItemDTO.setDescription(menuItem.getDescription());
        menuItemDTO.setPrice(String.valueOf(menuItem.getPrice()));
        return menuItemDTO;

    }
}
