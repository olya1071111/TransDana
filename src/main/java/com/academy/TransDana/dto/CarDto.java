package com.academy.TransDana.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Integer id;
    private String number;
    private Integer tonnage;
    private String status;
}
