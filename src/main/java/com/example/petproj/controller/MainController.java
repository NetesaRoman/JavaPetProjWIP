package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.User;
import com.example.petproj.model.UserRole;
import com.example.petproj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


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


    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/userProfile")
    public String userProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("user", user);
        model.addAttribute("avatar", new String(user.getImageData()));

        return "userProfile";
    }

    @GetMapping("/userProfile/edit")
    public String editProfile(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("user", user);
        model.addAttribute("avatar", new String(user.getImageData()));

        return "editProfile";
    }

    @PostMapping("/userProfile/edit")
    public String postEditProfile(Model model, Principal principal,
                                  @RequestParam("name") String name, @RequestParam("surname") String surname,
                                  @RequestParam("phone") String phone,
                                  @RequestParam(value = "avatar", required = false) MultipartFile avatarFile) throws IOException {

        String username = principal.getName();
        User user = userService.findByUserName(username);
        byte[] byteArr = Base64Utils.encode(avatarFile.getBytes());


            userService.updateUser(user.getId(),name, surname, phone, byteArr);



        return welcome();
    }


}
