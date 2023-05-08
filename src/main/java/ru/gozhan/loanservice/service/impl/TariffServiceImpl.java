package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.repository.TariffRepository;
import ru.gozhan.loanservice.service.TariffService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    private final TariffRepository tariffRepository;

    @Override
    public List<Tariff> getAllTariffs() {
        return tariffRepository.getAllTariffs();
    }

    @Override
    public String getTypeById(Long id) {
        return tariffRepository.getTypeById(id);
    }

}
