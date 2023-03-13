package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.User;
import com.example.petproj.model.UserRole;
import com.example.petproj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

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
    public String registerUserAccount(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                            @RequestParam("phone") String phone, @RequestParam("email") String email,
                                            @RequestParam("password1") String password1,
                                            @RequestParam("password2") String password2){

        if(password1.equals(password2)){
            userService.registerNewUserAccount(new UserDto( name, surname, phone, email, password1, UserRole.USER));
        }

        return "login";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("user", user);

        return "userProfile";
    }

    @GetMapping("/threads")
    public String threads(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "threads";
    }
}
