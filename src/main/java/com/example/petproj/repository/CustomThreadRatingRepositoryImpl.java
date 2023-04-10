package com.example.petproj.repository;

import com.example.petproj.model.ThreadRating;
import com.example.petproj.model.VoteThread;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

/*
 *
 * @author Roman Netesa
 *
 */
public class CustomThreadRatingRepositoryImpl implements CustomThreadRatingRepository{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ThreadRating> findAllByThreadName(String threadName) {
        Query query = entityManager
                .createQuery("select v from ThreadRating v where v.voteThread.name like : threadName")
                .setParameter("threadName", threadName);
        return (List<ThreadRating>) query.getResultList();
    }
}
