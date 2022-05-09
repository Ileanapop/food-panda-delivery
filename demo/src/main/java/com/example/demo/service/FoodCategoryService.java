package com.example.demo.service;


import com.example.demo.model.FoodCategory;
import com.example.demo.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    private final static Logger LOGGER = Logger.getLogger(FoodCategoryService.class.getName());


    /**
     * Method for retrieving the categories
     * @return the available food categories
     */
    public List<String> getAllCategories(){

        List<String> categories = new ArrayList<>();

        LOGGER.info("Requesting existing food categories");
        List<FoodCategory> foodCategories = foodCategoryRepository.findAll();

        for(FoodCategory categoryType : foodCategories){
            categories.add(categoryType.getName());
        }
        return categories;
    }
}
