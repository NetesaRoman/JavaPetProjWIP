package com.example.petproj.repository;

/*
 *
 * @author Roman Netesa
 *
 */


import com.example.petproj.model.VoteThread;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteThreadRepository extends CrudRepository<VoteThread, Integer> {
}
