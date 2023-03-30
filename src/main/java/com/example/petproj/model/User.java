package com.example.petproj.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<VoteThread> votes = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Column(name = "avatar")
    private byte[] imageData;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<ThreadRating> ratings;



}
