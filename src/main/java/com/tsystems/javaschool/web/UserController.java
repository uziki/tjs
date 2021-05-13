package com.tsystems.javaschool.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/")
public class UserController {

    @GetMapping("/register")
    public String register () {
        return "register";
    }
}