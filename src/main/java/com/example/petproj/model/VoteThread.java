package com.example.petproj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @Column(name = "image")
    private byte[] imageData;

    @Column(name="creation_date")
    private LocalDate date;

    @Column(name="creation_time")
    private LocalTime time;

    @ManyToMany(mappedBy = "liked")
    private List<User> likeUsers;

    @ManyToMany(mappedBy = "disliked")
    private List<User> dislikeUsers;

}
