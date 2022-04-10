package com.example.demo.service;


import com.example.demo.model.FoodCategory;
import com.example.demo.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    public List<String> getAllCategories(){

        List<String> categories = new ArrayList<>();

        List<FoodCategory> foodCategories = foodCategoryRepository.findAll();

        for(FoodCategory categoryType : foodCategories){
            categories.add(categoryType.getName());
        }
        return categories;
    }
}
