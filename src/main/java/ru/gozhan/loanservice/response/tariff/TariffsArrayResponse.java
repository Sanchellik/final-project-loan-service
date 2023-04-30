package ru.gozhan.loanservice.response.tariff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gozhan.loanservice.model.Tariff;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TariffsArrayResponse {

    private List<TariffResponse> tariffs;

}
