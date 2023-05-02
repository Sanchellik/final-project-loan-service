package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;

public class OrderNotFoundException extends BaseException {

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }

}
