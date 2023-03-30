package com.example.petproj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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


    @Column(name = "image")
    private byte[] imageData;

    @Column(name="creation_date")
    private LocalDate date;

    @Column(name="creation_time")
    private LocalTime time;

    @OneToMany(mappedBy = "voteThread", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<ThreadRating> ratings;

}
