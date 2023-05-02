package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;

public class LoanAlreadyApprovedException extends BaseException {

    public LoanAlreadyApprovedException() {
        super(ErrorCode.LOAN_ALREADY_APPROVED);
    }

}
