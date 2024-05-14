package com.academy.TransDana.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    @NotNull
    @NotEmpty(message = "The field should not be empty")
    private String name;
    @NotNull
    @NotEmpty(message = "The field should not be empty")
    private String job;
}
