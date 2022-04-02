package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "administrator")
public class Administrator {

    @Id
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public Administrator(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Administrator(){

    }
}