package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "delivery_zone")
public class DeliveryZone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_zone", updatable = false, nullable = false)
    private Integer idZone;

    @Column(name = "zone_name")
    private String zoneName;

    @ManyToMany(mappedBy = "deliveryZones")
    private List<Restaurant> restaurants;
}