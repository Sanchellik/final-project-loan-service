package ru.gozhan.loanservice.exception.tariff;

import ru.gozhan.loanservice.constant.Code;

public class TariffNotFoundException extends RuntimeException {

    public TariffNotFoundException() {
        super(Code.TARIFF_NOT_FOUND.getMessage());
    }

}
