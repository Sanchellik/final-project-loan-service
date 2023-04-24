package ru.gozhan.loanservice.repository;

import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Tariff;

import java.util.List;

@Repository
public interface TariffRepository {

    List<Tariff> getAllTariffs();

}
