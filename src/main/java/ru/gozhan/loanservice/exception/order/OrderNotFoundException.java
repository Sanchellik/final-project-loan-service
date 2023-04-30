package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.Code;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super(Code.ORDER_NOT_FOUND.getMessage());
    }

}
