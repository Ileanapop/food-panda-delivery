package com.example.demo.dto;


import com.example.demo.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodCategoryDTO {

    private String name;

    private List<MenuItemDTO> menuItems;

}
