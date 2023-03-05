package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
public class MainController {

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/registration")
    public String registration(WebRequest request, Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
