package com.example.petproj.controller;

import com.example.petproj.dto.UserButtonDto;
import com.example.petproj.model.User;
import com.example.petproj.service.UserService;
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

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/userProfile")
    public String userProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("client", user);
        model.addAttribute("user", user);
        model.addAttribute("avatar", new String(user.getImageData()));
        model.addAttribute("isOwner", true);
        return "userProfile";
    }

    @GetMapping("/userProfile/{id}")
    public String anyUserProfile(@PathVariable("id") Integer id, Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);
        User target = userService.findById(id);
        boolean isOwner = user.equals(target);

        model.addAttribute("client", user);
        model.addAttribute("user", target);
        model.addAttribute("avatar", new String(target.getImageData()));
        model.addAttribute("isOwner", isOwner);
        return "userProfile";
    }

    @GetMapping("users")
    public String showUsers(Model model, Principal principal){

        List<UserButtonDto> users = userService.findAllForButtons();


        String username = principal.getName();
        User userClient = userService.findByUserName(username);


        model.addAttribute("users", users);
        model.addAttribute("userClient", userClient);
        return "users";
    }

    @GetMapping("/userProfile/edit")
    public String editProfile(Model model, Principal principal) {
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



        userService.updateUser(user.getId(), name, surname, phone, byteArr);
        user = userService.findByUserName(username);


        model.addAttribute("client", user);
        model.addAttribute("user", user);
        model.addAttribute("avatar", new String(user.getImageData()));
        model.addAttribute("isOwner", true);

        return "redirect:/userProfile";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, Principal principal){
        userService.deleteUser(id);
        return showUsers(model, principal);
    }
}
