package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffRepository {

    List<Tariff> getAllTariffs();

    boolean existsById(Long id);

    Optional<String> getTypeById(Long id);

}
