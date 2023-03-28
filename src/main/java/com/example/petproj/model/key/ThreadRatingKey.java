package com.example.petproj.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 *
 * @author Roman Netesa
 *
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadRatingKey implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "thread_id")
    private Integer threadId;

}
