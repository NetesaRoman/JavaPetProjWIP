package com.example.petproj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *
 * @author Roman Netesa
 *
 */
@Entity
@Table(schema = "vote_site", name = "vote_thread")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Column
    private Integer likes;

    @Column
    private Integer dislikes;

}
