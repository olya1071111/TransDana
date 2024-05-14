package com.academy.TransDana.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private Integer id;
    @NotEmpty(message = "Поле не должно быть пустым")
    private String username;
    @NotEmpty(message = "Поле не должно быть пустым")
    private String role;
    private UserDto user;

}
