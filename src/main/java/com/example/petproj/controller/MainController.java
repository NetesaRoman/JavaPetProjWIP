package com.example.petproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
