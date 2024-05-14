package com.academy.TransDana.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "The field should not be empty")
    private String name;
    @NotEmpty(message = "The field should not be empty")
    private String job;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id", nullable = false)
//    private Login  login;
   }
