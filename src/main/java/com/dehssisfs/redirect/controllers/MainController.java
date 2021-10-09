package com.dehssisfs.redirect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    static String staticIp = "37.1.40.16";

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @ResponseBody
    @GetMapping("/setip/{ip}")
    public String newIp(@PathVariable(value = "ip") String ip, Model model) {
        staticIp = ip;
//        System.out.println(staticIp);
        model.addAttribute("title", "Установка IP");
        return "Ok, set IP: " + ip;
    }

    @ResponseBody
    @GetMapping("/getip")
    public String getIp(Model model) {
        model.addAttribute("title", "Текущий IP");
        return staticIp;
    }

    @GetMapping("/workpermit/loto")
    public String wpl(Model model) {
        return "redirect:http://" + staticIp + ":8080/workpermit/loto";
    }

    @GetMapping("/workpermit/loto/allview")
    public String allviewLotoNd(Model model) {
        return "redirect:http://" + staticIp + ":8080//workpermit/loto/allview";
    }

    @GetMapping("/waste/shiftreport")
    public String wasteshiftreport(Model model) {
        return "redirect:http://" + staticIp + ":8080//waste/shiftreport";
    }


}
