package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_restaurant", updatable = false, nullable = false)
    private Integer idRestaurant;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    //@ManyToMany(cascade = CascadeType.ALL)
   // @JoinTable(name = "restaurant_menuitem",
     //       joinColumns = @JoinColumn(name = "id_restaurant"),
     //       inverseJoinColumns = @JoinColumn(name = "id_item"))
   // private List<MenuItem> menuItems;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_zones",
            joinColumns = @JoinColumn(name = "id_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "id_zone"))
    private List<DeliveryZone> deliveryZones;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

}