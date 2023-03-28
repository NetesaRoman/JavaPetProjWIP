package com.example.petproj.service;

import com.example.petproj.model.ThreadRating;
import com.example.petproj.model.User;
import com.example.petproj.model.VoteThread;
import com.example.petproj.model.key.ThreadRatingKey;
import com.example.petproj.repository.ThreadRatingRepository;
import com.example.petproj.repository.UserRepository;
import com.example.petproj.repository.VoteThreadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 *
 * @author Roman Netesa
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ThreadRatingService {
    private final VoteThreadRepository voteThreadRepository;
    private final UserRepository userRepository;
    private final ThreadRatingRepository threadRatingRepository;

    public void rate(Integer userId, Integer threadId, Integer mark){
        if(userRepository.findById(userId).isPresent() && voteThreadRepository.findById(threadId).isPresent()){
        User user = userRepository.findById(userId).get();
        VoteThread voteThread = voteThreadRepository.findById(threadId).get();

        ThreadRatingKey ratingKey = new ThreadRatingKey(user.getId(), voteThread.getId());

        ThreadRating threadRating;
        if(threadRatingRepository.findById(ratingKey).isPresent()){
            threadRating = threadRatingRepository.findById(ratingKey).get();
            threadRating.setRating(mark);
        }else {
            threadRating = new ThreadRating();
            threadRating.setId(ratingKey);
            threadRating.setRating(mark);
            threadRating.setUser(user);
            threadRating.setVoteThread(voteThread);
        }
        threadRatingRepository.save(threadRating);

        }else {
            log.error("Couldn't find user or thread");
        }



    }
}
