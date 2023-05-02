package ru.gozhan.loanservice.exception.tariff;

import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;

public class TariffNotFoundException extends BaseException {

    public TariffNotFoundException() {
        super(ErrorCode.TARIFF_NOT_FOUND);
    }

}
