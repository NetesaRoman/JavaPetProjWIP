package com.example.petproj.dto;

import com.example.petproj.model.ThreadRating;
import com.example.petproj.model.User;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
 *
 * @author Roman Netesa
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteThreadDto {

    private Integer id;

    private String name;

    private String description;

    private User author;

    private Integer likes;

    private Integer dislikes;

    private byte[] imageData;

    private LocalDate date;

    private LocalTime time;

    private List<User> likeUsers;

    private List<User> dislikeUsers;

    private Set<ThreadRating> ratings;


    public VoteThreadDto(String name, String description, User author, byte[] imageData) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.imageData = imageData;
        ratings = new HashSet<>();

    }


}
