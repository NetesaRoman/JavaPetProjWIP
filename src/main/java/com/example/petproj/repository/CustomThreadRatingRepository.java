package com.example.petproj.repository;

import com.example.petproj.model.ThreadRating;
import com.example.petproj.model.VoteThread;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *
 * @author Roman Netesa
 *
 */

public interface CustomThreadRatingRepository {
    List<ThreadRating> findAllByThreadName(String threadName);
}
