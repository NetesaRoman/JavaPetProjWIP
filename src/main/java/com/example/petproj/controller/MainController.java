package com.example.petproj.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



/*
 *
 * @author Roman Netesa
 *
 */
@Controller
@Slf4j
public class MainController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
