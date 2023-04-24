package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.Tariff;

import java.util.List;

public interface TariffRepository {

    List<Tariff> getAllTariffs();

}
