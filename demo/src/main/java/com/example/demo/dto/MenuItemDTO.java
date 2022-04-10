package com.example.demo.dto;

import com.example.demo.model.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class MenuItemDTO {

    private String itemName;

    private String description;

    @Positive
    private Double price;

    private String category;

    private String administrator;

}
