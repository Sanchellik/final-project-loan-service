package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gozhan.loanservice.service.OrderService;

@RestController
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

//    @PostMapping("/order")
//    public

}
