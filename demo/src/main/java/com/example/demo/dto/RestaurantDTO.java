package com.example.demo.dto;

import com.example.demo.model.Administrator;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.MenuItem;
import com.example.demo.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class RestaurantDTO {

    private String name;

    private String location;

    private Administrator administrator;

    private List<DeliveryZone> deliveryZones;

}
