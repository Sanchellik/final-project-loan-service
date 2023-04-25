package ru.gozhan.loanservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/loan-service")
    public String index() {
        return "redirect:loan-service.html";
    }

}
