package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;

public class TryLaterException extends BaseException {

    public TryLaterException() {
        super(ErrorCode.TRY_LATER);
    }

}
