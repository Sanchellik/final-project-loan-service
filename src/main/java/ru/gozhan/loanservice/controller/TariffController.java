package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.service.TariffService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class TariffController {

    private final TariffService tariffService;

    @GetMapping("getTariffs")
    public ResponseEntity<Map<String, Object>> getAllTariffs() {
        List<Tariff> tariffs = tariffService.getAllTariffs();

        Map<String, Object> response = new HashMap<>();
        response.put("data", Collections.singletonMap("tariffs", tariffs));

        return ResponseEntity.ok(response);
    }
//    public ResponseEntity<List<Tariff>> getAllTariffs() {
//        List<Tariff> tariffs = tariffService.getAllTariffs();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(tariffs);
//    }

}
