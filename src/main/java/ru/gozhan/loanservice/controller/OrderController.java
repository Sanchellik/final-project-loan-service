package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gozhan.loanservice.service.OrderService;

@Controller
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @GetMapping("order") //TODO "order-view"
//    public String createOrder() {
//        return "redirect:order";
//    }

}
