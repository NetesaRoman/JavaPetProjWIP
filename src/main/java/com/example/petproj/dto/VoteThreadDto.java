package com.example.petproj.dto;

import com.example.petproj.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public VoteThreadDto(String name, String description, User author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }
}
