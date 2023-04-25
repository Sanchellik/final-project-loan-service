package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gozhan.loanservice.service.TariffService;

@Controller
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class TariffController {

    private final TariffService tariffService;

    @GetMapping("getTariffs-view")
    public String getTariffs(Model model) {
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "getTariffs";
    }

}
