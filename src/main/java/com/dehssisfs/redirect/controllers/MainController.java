package com.dehssisfs.redirect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    static String staticIp = "";

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @ResponseBody
    @GetMapping("/ip/{ip}")
    public String newIp(@PathVariable(value = "ip") String ip, Model model) {
        staticIp = ip;
//        System.out.println(staticIp);
        model.addAttribute("title", "Главная страница");
        return "Ok";
    }

    @GetMapping("/workpermit/loto")
    public String wpl(Model model) {
        return "redirect:http://" + staticIp + ":8080/workpermit/loto";
    }

}
