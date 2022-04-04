package com.example.demo.service;


import com.example.demo.dto.MenuItemDTO;
import com.example.demo.model.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.utils.MenuItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(){

    }

    public MenuItemDTO addMenuItem(MenuItemDTO menuItemDTO){

        MenuItemMapper menuItemMapper = new MenuItemMapper();
        MenuItem newMenuItem = menuItemMapper.convertFromDTO(menuItemDTO);
        return menuItemMapper.convertToDTO(menuItemRepository.save(newMenuItem));
    }

    public List<MenuItemDTO> findMenuItemsByCategory(String category){
        List<MenuItemDTO> menuItemDTOList = new ArrayList<>();
        List<MenuItem> menuItems = menuItemRepository.findMenuItemsByCategory(category);

        MenuItemMapper menuItemMapper = new MenuItemMapper();

        for(MenuItem menuItem: menuItems){
            menuItemDTOList.add(menuItemMapper.convertToDTO(menuItem));
        }
        return menuItemDTOList;
    }
}
