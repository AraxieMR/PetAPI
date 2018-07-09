package com.manheim.apprenticeship.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @Value("WELCOME TO PETS BOARDING HOUSE !")
    String appName;

    @RequestMapping("/")
    public  String homePage(Model model){
        model.addAttribute("appName",appName);
        return "home";
    }
}
