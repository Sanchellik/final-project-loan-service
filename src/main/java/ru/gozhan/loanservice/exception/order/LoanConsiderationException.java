package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;

public class LoanConsiderationException extends BaseException {

    public LoanConsiderationException() {
        super(ErrorCode.LOAN_CONSIDERATION);
    }

}
