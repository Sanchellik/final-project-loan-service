package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.gozhan.loanservice.response.Response;
import ru.gozhan.loanservice.response.SuccessResponse;
import ru.gozhan.loanservice.response.tariff.TariffResponse;
import ru.gozhan.loanservice.response.tariff.TariffsArrayResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
