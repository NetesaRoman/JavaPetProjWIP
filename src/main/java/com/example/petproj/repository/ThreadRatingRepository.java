package com.example.petproj.repository;

import com.example.petproj.model.ThreadRating;
import com.example.petproj.model.key.ThreadRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *
 * @author Roman Netesa
 *
 */
@Repository
public interface ThreadRatingRepository extends JpaRepository<ThreadRating, ThreadRatingKey>, CustomThreadRatingRepository {


}
