package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_item", updatable = false, nullable = false)
    private Integer idMenuItem;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private FoodCategory category;

    //@ManyToMany(mappedBy = "menuItems")
    //private List<Restaurant> restaurants;

    @ManyToMany(mappedBy = "orderMenuItems")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;
}
