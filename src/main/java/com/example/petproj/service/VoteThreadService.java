package com.example.petproj.service;

import com.example.petproj.dto.VoteThreadDto;
import com.example.petproj.model.VoteThread;
import com.example.petproj.repository.VoteThreadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 *
 * @author Roman Netesa
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VoteThreadService {
    private final VoteThreadRepository voteThreadRepository;

    public void addNewThread(VoteThreadDto voteThreadDto){
        VoteThread voteThread = new VoteThread();
        log.info("service new thread");
        voteThread.setAuthor(voteThreadDto.getAuthor());
        log.info("service got author " + voteThread.getAuthor().getName());
        voteThread.setDislikes(0);
        log.info("service disLikes");
        voteThread.setLikes(0);
        log.info("service likes");
        voteThread.setName(voteThreadDto.getName());
        log.info("service name " + voteThread.getName());
        voteThread.setDescription(voteThreadDto.getDescription());
        log.info("service description " + voteThread.getDescription());
        voteThreadRepository.save(voteThread);
        log.info("service save");
        voteThreadDto.setId(voteThread.getId());
    }


    public List<VoteThread> findAll() {
       return (List<VoteThread>) voteThreadRepository.findAll();
    }

    public Optional<VoteThread> findById(Integer id) {
        return voteThreadRepository.findById(id);
    }
}
