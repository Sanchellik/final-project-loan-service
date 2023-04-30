package ru.gozhan.loanservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.response.tariff.TariffResponse;

@Component
@Mapper(componentModel = "spring")
public interface TariffMapper {

    @Mapping(source = "interestRate", target = "interest_rate")
    TariffResponse toResponse(Tariff tariff);

}
