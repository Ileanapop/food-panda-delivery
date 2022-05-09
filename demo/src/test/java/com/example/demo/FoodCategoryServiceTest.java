package com.example.demo;


import com.example.demo.dto.FoodCategoryDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.FoodCategory;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.repository.FoodCategoryRepository;
import com.example.demo.service.DeliveryZoneService;
import com.example.demo.service.FoodCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FoodCategoryServiceTest {

    @InjectMocks
    private FoodCategoryService foodCategoryService;

    @Mock
    private FoodCategoryRepository foodCategoryRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCategoriesShouldReturnListCategories(){

        List<FoodCategory> categories = new ArrayList<>();

        FoodCategory foodCategory1 = new FoodCategory();
        foodCategory1.setIdCategory(1);
        foodCategory1.setName("Category1");

        FoodCategory foodCategory2 = new FoodCategory();
        foodCategory2.setIdCategory(2);
        foodCategory2.setName("Category2");

        FoodCategory foodCategory3 = new FoodCategory();
        foodCategory3.setIdCategory(3);
        foodCategory3.setName("Category3");

        categories.add(foodCategory1);
        categories.add(foodCategory2);
        categories.add(foodCategory3);


        Mockito.when(foodCategoryRepository.findAll()).thenReturn(categories);

        List<String> allCategories = foodCategoryService.getAllCategories();

        Assertions.assertEquals(categories.size(),allCategories.size());
        Assertions.assertEquals("Category1",allCategories.get(0));
        Assertions.assertEquals("Category2",allCategories.get(1));
        Assertions.assertEquals("Category3",allCategories.get(2));
    }

}
