package com.example.petproj.repository;

import com.example.petproj.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 *
 * @author Roman Netesa
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findFirstByName(String email);

    boolean existsUserByName(String email);
}
