package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.Code;

public class TryLaterException extends RuntimeException {

    public TryLaterException() {
        super(Code.TRY_LATER.getMessage());
    }

}
