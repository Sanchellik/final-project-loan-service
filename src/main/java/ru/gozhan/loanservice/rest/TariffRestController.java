package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.response.TariffsResponse;
import ru.gozhan.loanservice.service.TariffService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class TariffRestController {

    private final TariffService tariffService;

//    @GetMapping("getTariffs")
//    public ResponseEntity<Map<String, Object>> getAllTariffs() {
//        List<Tariff> tariffs = tariffService.getAllTariffs();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("data", Collections.singletonMap("tariffs", tariffs));
//
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("getTariffs")
    public ResponseEntity<TariffsResponse> getAllTariffs() {
        List<Tariff> tariffs = tariffService.getAllTariffs();

        TariffsResponse response = new TariffsResponse(tariffs);

        return ResponseEntity.ok(response);
    }

}
