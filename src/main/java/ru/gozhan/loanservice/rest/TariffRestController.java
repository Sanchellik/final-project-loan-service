package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.response.Response;
import ru.gozhan.loanservice.response.SuccessResponse;
import ru.gozhan.loanservice.response.tariff.TariffsResponse;
import ru.gozhan.loanservice.service.TariffService;

import java.util.List;

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
    public ResponseEntity<Response> getAllTariffs() {
        List<Tariff> tariffList = tariffService.getAllTariffs();

        return new ResponseEntity<>(
                SuccessResponse.<TariffsResponse>builder().data(
                                TariffsResponse.builder().tariffs(tariffList).build()
                        )
                        .build(), HttpStatus.OK
        );
    }

}
