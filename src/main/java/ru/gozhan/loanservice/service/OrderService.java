package ru.gozhan.loanservice.service;

import ru.gozhan.loanservice.exception.order.LoanAlreadyApprovedException;
import ru.gozhan.loanservice.exception.order.LoanConsiderationException;
import ru.gozhan.loanservice.exception.order.TryLaterException;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;

import java.util.UUID;

public interface OrderService {

    UUID orderProcessing(Long userId, Long tariffId) throws TariffNotFoundException, LoanConsiderationException, LoanAlreadyApprovedException, TryLaterException;

}
