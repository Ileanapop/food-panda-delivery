package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_order", updatable = false, nullable = false)
    private Integer idMenuItem;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_menuitem",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<MenuItem> orderMenuItems;
}
