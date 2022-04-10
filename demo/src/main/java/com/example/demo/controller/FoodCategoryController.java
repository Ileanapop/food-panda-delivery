package com.example.demo.controller;


import com.example.demo.dto.ZoneDTO;
import com.example.demo.service.DeliveryZoneService;
import com.example.demo.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foodCategories")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @GetMapping("/getAllCategories")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getDeliveryZones() {

       return foodCategoryService.getAllCategories();

    }
}
