package com.example.petproj.controller;

import com.example.petproj.model.User;
import com.example.petproj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


}
