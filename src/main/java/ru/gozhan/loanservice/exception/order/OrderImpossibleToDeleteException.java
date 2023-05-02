package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;

public class OrderImpossibleToDeleteException extends BaseException {

    public OrderImpossibleToDeleteException() {
        super(ErrorCode.ORDER_IMPOSSIBLE_TO_DELETE);
    }

}
