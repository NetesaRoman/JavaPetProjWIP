package com.example.petproj.dto;

import com.example.petproj.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


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


    public VoteThreadDto(String name, String description, User author, byte[] imageData) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.imageData = imageData;
    }
}
