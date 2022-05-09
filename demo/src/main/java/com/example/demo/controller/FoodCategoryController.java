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
import java.util.logging.Logger;

@RestController
@RequestMapping("/foodCategories")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    private final static Logger LOGGER = Logger.getLogger(FoodCategoryController.class.getName());

    @GetMapping("/getAllCategories")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getDeliveryZones() {

        LOGGER.info("Start request to get all food categories");
       return foodCategoryService.getAllCategories();

    }
}
