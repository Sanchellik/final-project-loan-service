package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.Code;

public class OrderImpossibleToDeleteException extends RuntimeException {

    public OrderImpossibleToDeleteException() {
        super(Code.ORDER_IMPOSSIBLE_TO_DELETE.getMessage());
    }

}
