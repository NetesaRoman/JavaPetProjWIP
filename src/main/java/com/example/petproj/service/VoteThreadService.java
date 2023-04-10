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

        voteThread.setImageData(voteThreadDto.getImageData());
        log.info("service likes");
        voteThread.setName(voteThreadDto.getName());
        log.info("service name " + voteThread.getName());
        voteThread.setDescription(voteThreadDto.getDescription());
        log.info("service description " + voteThread.getDescription());
        voteThread.setDate(LocalDate.now());
        voteThread.setTime(LocalTime.now());
        voteThread.setRatings(voteThreadDto.getRatings());
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

        result.setAuthorName(voteThread.getAuthor().getName());
        result.setImageData(new String(voteThread.getAuthor().getImageData()));
        result.setAuthor(voteThread.getAuthor());
        result.setRating(voteThread.getRating());

        return result;
    }

    public Optional<VoteThread> findById(Integer id) {
        return voteThreadRepository.findById(id);
    }

    public void deleteThread(Integer id){
        voteThreadRepository.deleteById(id);
    }

    public void deleteThread(VoteThreadDto voteThreadDto){
        voteThreadRepository.deleteById(voteThreadDto.getId());
    }

    public void deleteThread(VoteThread voteThread){
        voteThreadRepository.delete(voteThread);
    }



}
