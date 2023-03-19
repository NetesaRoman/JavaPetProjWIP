package com.example.petproj.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

/*
 *
 * @author Roman Netesa
 *
 */
@Entity
@Table(schema = "vote_site")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String password;


    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Column(name = "avatar", length = 1000)
    private byte[] imageData;



}
