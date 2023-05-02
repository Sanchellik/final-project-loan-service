package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.gozhan.loanservice.response.success.SuccessResponse;
import ru.gozhan.loanservice.response.success.tariff.TariffsArrayResponse;

@Controller
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class TariffController {

//    private final TariffService tariffService;

    @GetMapping("getTariffs-view")
    public String getTariffs(Model model) {

        RestTemplate restTemplate = new RestTemplate();

        String jsonUrl = "http://localhost:8080/loan-service/getTariffs";

        ResponseEntity<SuccessResponse<TariffsArrayResponse>> response = restTemplate.exchange(jsonUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });

        model.addAttribute("tariffs", response.getBody().getData().getTariffs());

        return "getTariffs";
    }

}
