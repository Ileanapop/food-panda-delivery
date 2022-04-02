package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_status", updatable = false, nullable = false)
    private Integer idStatus;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;

    public OrderStatus(String name){
        this.name = name;
    }
}