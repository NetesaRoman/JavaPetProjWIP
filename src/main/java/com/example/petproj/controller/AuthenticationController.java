package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
@Slf4j
public class AuthenticationController {


    @Autowired
    private UserService userService;

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
            userService.registerNewUserAccount(new UserDto(name, surname, phone, email, password1, UserRole.USER, byteArr));


        }

        return "login";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
