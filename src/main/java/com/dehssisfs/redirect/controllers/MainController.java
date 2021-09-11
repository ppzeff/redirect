package com.dehssisfs.redirect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/workpermit/loto")

    public String wpl(Model model) {
        String ip = "http://" + "37.1.3.194" + ":8080/";

        return "redirect:" + ip + "workpermit/loto";
    }

    @PostMapping("/workpermit/loto/regLOTO")
    public String wplr(Model model) {
        return "redirect:http://46.249.7.119:8080/regLOTO";
    }

}
