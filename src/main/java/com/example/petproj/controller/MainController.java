package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
import com.example.petproj.dto.VoteThreadDto;
import com.example.petproj.model.User;
import com.example.petproj.model.UserRole;
import com.example.petproj.model.VoteThread;
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
import org.springframework.web.context.request.WebRequest;
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
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private VoteThreadService voteThreadService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/registration")
    public String registration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                      @RequestParam("phone") String phone, @RequestParam("email") String email,
                                      @RequestParam(value = "avatar", required = false) MultipartFile avatarFile,
                                      @RequestParam("password1") String password1,
                                      @RequestParam("password2") String password2) throws IOException {


        byte[] byteArr = Base64Utils.encode(avatarFile.getBytes());

        if (password1.equals(password2)) {
            User savedUser = userService.registerNewUserAccount(
                    new UserDto(name, surname, phone, email, password1, UserRole.USER, byteArr));


        }

        return "login";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("user", user);
        model.addAttribute("avatar", new String(user.getImageData()));

        return "userProfile";
    }

    @GetMapping("/threads")
    public String threads(Model model) {
        List<VoteThread> votes = voteThreadService.findAll();
        model.addAttribute("votes", votes);
        return "threads";
    }

    @GetMapping("/threads/my")
    public String threadsMy(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);

        List<VoteThread> votes = voteThreadService.findAll().stream().filter(v -> v.getAuthor().equals(user)).toList();


        model.addAttribute("votes", votes);
        return "threads";
    }

    @GetMapping("/create")
    public String threadsCreate() {

        return "createThread";
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


        List<VoteThread> votes = voteThreadService.findAll();
        model.addAttribute("votes", votes);
        return "threads";
    }


    @GetMapping("/showThread/{id}")
    public String showThread(@PathVariable("id") Integer id, Model model) {
        Optional<VoteThread> vote = voteThreadService.findById(id);
        model.addAttribute("vote", vote.get());

        model.addAttribute("image", new String(vote.get().getImageData()));

        return "showThread";
    }


    @GetMapping("/showThread/random")
    public String showRandomThread(Model model) {

        Random random = new Random();
        List<VoteThread> votes = voteThreadService.findAll();
        VoteThread vote = votes.get(random.nextInt(votes.size()));
        model.addAttribute("vote", vote);

        return "showThread";
    }
}
