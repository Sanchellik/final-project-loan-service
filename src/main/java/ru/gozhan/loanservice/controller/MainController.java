package ru.gozhan.loanservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "index";
    }

}
