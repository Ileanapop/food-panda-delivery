package com.example.demo;


import com.example.demo.dto.MenuItemDTO;
import com.example.demo.model.FoodCategory;
import com.example.demo.model.MenuItem;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.FoodCategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.FoodCategoryService;
import com.example.demo.service.MenuItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private FoodCategoryRepository foodCategoryRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private AdministratorRepository administratorRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void findMenuItemsByExistingCategoryShouldReturnMenuItems(){

        List<MenuItem> menuItems = new ArrayList<>();

        MenuItem menuItem1 = new MenuItem();
        menuItem1.setCategory(new FoodCategory("category"));
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setCategory(new FoodCategory("category"));

        menuItems.add(menuItem1);
        menuItems.add(menuItem2);

        Mockito.when(menuItemRepository.findMenuItemsByCategory(Mockito.anyString())).thenReturn(menuItems);

        List<MenuItemDTO> returnedMenuItems = menuItemService.findMenuItemsByCategory("category");

        Assertions.assertEquals(menuItems.size(),returnedMenuItems.size());

    }


}
