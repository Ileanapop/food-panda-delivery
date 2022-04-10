package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotNull(message = "first name must not be null")
    @Size(min = 1, max = 255, message = "First Name '${validatedValue}' must be between {min} and {max} characters long")
    private String firstName;

    @NotNull(message = "last name must not be null")
    @Size(min = 1, max = 255, message = "Last Name '${validatedValue}' must be between {min} and {max} characters long")
    private String lastName;

    @NotNull(message = "email must not be null")
    @Size(min = 1, max = 255, message = "Email '${validatedValue}' must be between {min} and {max} characters long")
    @Email(regexp = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;
}