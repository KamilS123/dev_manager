package com.kamil.dev_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Max(30)
    private String username;

    @Column
    @NotNull
    @Max(30)
    private String surname;

    @Column
    @Email
    @NotNull
    private String email;

    @Column
    @NotNull
    @Max(30)
    @Min(5)
    private String password;

    @Column
    private Integer year_of_studies;

    @Column
    private String name_of_studies;

    @Column
    private Integer index_number;
}
