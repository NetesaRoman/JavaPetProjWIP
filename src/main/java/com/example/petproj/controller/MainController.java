package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.User;
import com.example.petproj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

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
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors) {


        User registered = userService.registerNewUserAccount(userDto);
        return new ModelAndView("successRegister", "user", userDto);

        // rest of the implementation
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
