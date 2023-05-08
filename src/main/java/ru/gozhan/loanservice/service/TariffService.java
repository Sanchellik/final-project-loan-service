package ru.gozhan.loanservice.service;

import ru.gozhan.loanservice.model.Tariff;

import java.util.List;

public interface TariffService {

    List<Tariff> getAllTariffs();

    String getTypeById(Long id);

}
