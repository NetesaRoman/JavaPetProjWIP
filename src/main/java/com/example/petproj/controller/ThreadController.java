package com.example.petproj.controller;

import com.example.petproj.dto.VoteThreadButtonDto;
import com.example.petproj.dto.VoteThreadDto;
import com.example.petproj.model.User;
import com.example.petproj.model.VoteThread;
import com.example.petproj.service.ThreadRatingService;
import com.example.petproj.service.UserService;
import com.example.petproj.service.VoteThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
@Slf4j
public class ThreadController {
    @Autowired
    private UserService userService;

    @Autowired
    private ThreadRatingService threadRatingService;

    @Autowired
    private VoteThreadService voteThreadService;

    @GetMapping("/create")
    public String threadsCreate() {

        return "createThread";
    }

    @GetMapping("/threads")
    public String threads(Model model, Principal principal) {
        List<VoteThreadButtonDto> votes = voteThreadService.findAllForButtons();
        model.addAttribute("votes", votes);

        String username = principal.getName();
        User user = userService.findByUserName(username);

        model.addAttribute("user", user.getId());
        return "threads";
    }

    @PostMapping("/create")
    public String create(Model model, Principal principal,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam(value = "threadImg", required = false) MultipartFile threadImageFile) throws IOException {

        byte[] byteArr = Base64Utils.encode(threadImageFile.getBytes());
        String username = principal.getName();
        log.info(username);
        User user = userService.findByUserName(username);
        log.info(user.toString());
        VoteThreadDto voteThreadDto = new VoteThreadDto(name, description, user, byteArr);
        log.info(voteThreadDto.toString());
        voteThreadService.addNewThread(voteThreadDto);
        log.info("done");


        return threads(model, principal);
    }

    @GetMapping("/threads/my")
    public String threadsMy(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);

        List<VoteThreadButtonDto> votes = voteThreadService.findAllForButtons().stream().filter(v -> v.getAuthor().equals(user)).toList();


        model.addAttribute("votes", votes);
        model.addAttribute("user", user.getId());
        return "threads";
    }


    @GetMapping("/showThread/{id}")
    public String showThread(@PathVariable("id") Integer id, Model model) {
        Optional<VoteThread> vote = voteThreadService.findById(id);
        model.addAttribute("vote", vote.get());

        model.addAttribute("image", new String(vote.get().getImageData()));

        return "showThread";
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable("id") Integer id, Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);

        threadRatingService.rate(user.getId(), id, 1);
        return showThread(id, model);


    }

    @PostMapping("/dislike/{id}")
    public String dislike(@PathVariable("id") Integer id, Model model, Principal principal) {

        String username = principal.getName();
        User user = userService.findByUserName(username);

        threadRatingService.rate(user.getId(), id, -1);

        return showThread(id, model);

    }

    @PostMapping("/deleteThread/{id}")
    public String deleteThread(@PathVariable("id") Integer id, Model model, Principal principal) {

        voteThreadService.deleteThread(id);

        return threads(model, principal);
    }

    @GetMapping("/showThread/random")
    public String showRandomThread(Model model) {

        Random random = new Random();
        List<VoteThread> votes = voteThreadService.findAll();
        VoteThread vote = votes.get(random.nextInt(votes.size()));
        model.addAttribute("vote", vote);

        model.addAttribute("image", new String(vote.getImageData()));
        return "showThread";
    }


}
