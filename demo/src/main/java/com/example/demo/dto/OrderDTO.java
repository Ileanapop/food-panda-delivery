package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull(message = "restaurant name must not be null")
    @Size(min = 1, max = 255, message = "Restaurant '${validatedValue}' must be between {min} and {max} characters long")
    private String restaurant;

    @NotNull(message = "Customer name must not be null")
    @Size(min = 1, max = 255, message = "Customer Name '${validatedValue}' must be between {min} and {max} characters long")
    private String customer;

    @NotEmpty(message = "Input foods list cannot be empty.")
    private List<String> foods;


}
