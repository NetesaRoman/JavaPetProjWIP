package com.example.petproj.service;

import com.example.petproj.dto.VoteThreadButtonDto;
import com.example.petproj.dto.VoteThreadDto;
import com.example.petproj.model.VoteThread;
import com.example.petproj.repository.VoteThreadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        voteThread.setImageData(voteThreadDto.getImageData());
        log.info("service likes");
        voteThread.setName(voteThreadDto.getName());
        log.info("service name " + voteThread.getName());
        voteThread.setDescription(voteThreadDto.getDescription());
        log.info("service description " + voteThread.getDescription());
        voteThread.setDate(LocalDate.now());
        voteThread.setTime(LocalTime.now());
        log.info(voteThread.getDate().toString());
        log.info(voteThread.getTime().toString());
        voteThreadRepository.save(voteThread);

        log.info("service save");
        voteThreadDto.setId(voteThread.getId());
    }


    public List<VoteThread> findAll() {
       return (List<VoteThread>) voteThreadRepository.findAll();
    }

    public List<VoteThreadButtonDto> findAllForButtons() {
        List<VoteThreadButtonDto> buttons = new ArrayList<>();
         voteThreadRepository.findAll().forEach(voteThread -> buttons.add(makeButtonDto(voteThread)));

         return buttons;
    }

    public VoteThreadButtonDto makeButtonDto(VoteThread voteThread){
        VoteThreadButtonDto result = new VoteThreadButtonDto();
        result.setDate(voteThread.getDate());
        result.setTime(voteThread.getTime());
        result.setName(voteThread.getName());
        result.setId(voteThread.getId());
        result.setLikes(voteThread.getLikes());
        result.setDislikes(voteThread.getDislikes());
        result.setAuthorName(voteThread.getAuthor().getName());
        result.setImageData(new String(voteThread.getAuthor().getImageData()));
        result.setAuthor(voteThread.getAuthor());

        return result;
    }

    public Optional<VoteThread> findById(Integer id) {
        return voteThreadRepository.findById(id);
    }


    public void like(VoteThreadDto voteThreadDto){

        VoteThread voteThread = new VoteThread();
        voteThread.setId(voteThreadDto.getId());
        voteThread.setAuthor(voteThreadDto.getAuthor());

        voteThread.setDislikes(voteThreadDto.getDislikes());

        voteThread.setLikes(voteThreadDto.getLikes() + 1);
        voteThread.setImageData(voteThreadDto.getImageData());

        voteThread.setName(voteThreadDto.getName());

        voteThread.setDescription(voteThreadDto.getDescription());

        voteThread.setDate(voteThreadDto.getDate());
        voteThread.setTime(voteThreadDto.getTime());

        voteThreadRepository.save(voteThread);


    }


    public void dislike(VoteThreadDto voteThreadDto){

        VoteThread voteThread = new VoteThread();
        voteThread.setId(voteThreadDto.getId());
        voteThread.setAuthor(voteThreadDto.getAuthor());

        voteThread.setDislikes(voteThreadDto.getDislikes() + 1);

        voteThread.setLikes(voteThreadDto.getLikes() );
        voteThread.setImageData(voteThreadDto.getImageData());

        voteThread.setName(voteThreadDto.getName());

        voteThread.setDescription(voteThreadDto.getDescription());

        voteThread.setDate(voteThreadDto.getDate());
        voteThread.setTime(voteThreadDto.getTime());

        voteThreadRepository.save(voteThread);


    }

    public void dislike(VoteThread voteThread){
        voteThread.setDislikes(voteThread.getDislikes() + 1);
        voteThreadRepository.save(voteThread);
    }
}
