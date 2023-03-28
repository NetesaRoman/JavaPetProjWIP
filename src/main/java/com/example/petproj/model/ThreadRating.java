package com.example.petproj.model;

import com.example.petproj.model.key.ThreadRatingKey;
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
@Table(schema = "vote_site", name = "thread_rating")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ThreadRating {

    @EmbeddedId
    private ThreadRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("threadID")
    @JoinColumn(name = "thread_id")
    private VoteThread voteThread;

    @Column(name = "rating")
    private Integer rating;

}
