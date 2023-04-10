package com.example.petproj.dto;

import com.example.petproj.model.User;
import jakarta.persistence.Column;
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
public class VoteThreadButtonDto {

    private Integer id;

    private String name;

    private String authorName;

    private Integer likes;

    private Integer dislikes;

    private String imageData;

    private LocalDate date;

    private LocalTime time;

    private User author;

    @Column
    private Integer rating;

}
