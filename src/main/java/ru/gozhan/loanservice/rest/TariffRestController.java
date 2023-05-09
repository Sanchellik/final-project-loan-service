package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.response.base.Response;
import ru.gozhan.loanservice.response.success.SuccessResponse;
import ru.gozhan.loanservice.response.success.tariff.TariffResponse;
import ru.gozhan.loanservice.response.success.tariff.TariffsArrayResponse;
import ru.gozhan.loanservice.service.TariffService;
import ru.gozhan.loanservice.util.TariffMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loan-service")
@RequiredArgsConstructor
@Slf4j
public class TariffRestController {

    private final TariffService tariffService;

    private final TariffMapper tariffMapper;

    @GetMapping("getTariffs")
    public ResponseEntity<Response> getAllTariffs() {
        log.info("/getTariffs START");

        List<Tariff> tariffs = tariffService.getAllTariffs();
        List<TariffResponse> tariffResponses = tariffs.stream()
                .map(tariffMapper::toResponse)
                .collect(Collectors.toList());

        TariffsArrayResponse response = TariffsArrayResponse.builder()
                .tariffs(tariffResponses)
                .build();

        log.info("/getTariffs FINISH");
        return new ResponseEntity<>(SuccessResponse.builder().data(response).build(), HttpStatus.OK);
    }

}
