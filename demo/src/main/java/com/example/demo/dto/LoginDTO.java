package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "username must not be null")
    @Size(min = 1, max = 255, message = "Username '${validatedValue}' must be between {min} and {max} characters long")
    private String username;

    @NotNull(message = "password must not be null")
    @Size(min = 1, max = 255, message = "Password '${validatedValue}' must be between {min} and {max} characters long")
    private String password;
}
