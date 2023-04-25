package ru.gozhan.loanservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gozhan.loanservice.model.Tariff;

import java.util.List;

@AllArgsConstructor
@Getter
public class TariffsResponse {

    private List<Tariff> tariffs;

}
