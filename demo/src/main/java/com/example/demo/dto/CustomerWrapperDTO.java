package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWrapperDTO {


    @NotNull(message = "must provide username and password")
    @Valid
    private LoginDTO loginDTO;

    @NotNull(message = "must provide customer details")
    @Valid
    private CustomerDTO customerDTO;

}
