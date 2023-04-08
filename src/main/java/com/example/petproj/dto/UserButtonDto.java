package com.example.petproj.dto;

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
public class UserButtonDto {

    private Integer id;
    private String name;
    private String secondName;
    private String imageData;
}
