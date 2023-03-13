package com.example.petproj.service;

import com.example.petproj.dto.VoteThreadDto;
import com.example.petproj.model.VoteThread;
import com.example.petproj.repository.VoteThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 *
 * @author Roman Netesa
 *
 */
@Service
@RequiredArgsConstructor
public class VoteThreadService {
    private final VoteThreadRepository voteThreadRepository;

    public VoteThreadDto addNewThread(VoteThreadDto voteThreadDto){
        VoteThread voteThread = new VoteThread();
        voteThread.setAuthor(voteThreadDto.getAuthor());
        voteThread.setDislikes(0);
        voteThread.setLikes(0);
        voteThread.setName(voteThreadDto.getName());
        voteThread.setDescription(voteThreadDto.getDescription());

        voteThreadRepository.save(voteThread);
        voteThreadDto.setId(voteThread.getId());
        return voteThreadDto;
    }
    

}
