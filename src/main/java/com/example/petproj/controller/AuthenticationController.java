package com.example.petproj.controller;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.UserRole;
import com.example.petproj.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

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


    private AuthenticationManager authenticationManager;


    @GetMapping("/registration")
    public String registration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public void registerUserAccount(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("surname") String surname,
                                    @RequestParam("phone") String phone, @RequestParam("email") String email,
                                    @RequestParam(value = "avatar", required = false) MultipartFile avatarFile,
                                    @RequestParam("password1") String password1,
                                    @RequestParam("password2") String password2) throws IOException {
        byte[] byteArr = null;
        if(avatarFile.isEmpty()) {
            try {

                InputStream inputStream = getClass().getResourceAsStream("/static/img/avatar_placeholder.png");
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);

                byteArr = Base64Utils.encode(baos.toByteArray());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            byteArr = Base64Utils.encode(avatarFile.getBytes());
        }





        if (password1.equals(password2)) {
            userService.registerNewUserAccount(new UserDto(name, surname, phone, email, password1, UserRole.USER, byteArr));

            try {
                request.login(email, password1);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }


    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
