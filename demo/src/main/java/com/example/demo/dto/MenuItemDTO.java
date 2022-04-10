package com.example.demo.dto;

import com.example.demo.model.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class MenuItemDTO {

    private String itemName;

    private String description;

    private String price;

    private String category;

    private String administrator;

}
