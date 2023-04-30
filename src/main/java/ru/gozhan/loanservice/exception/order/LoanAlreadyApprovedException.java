package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.Code;

public class LoanAlreadyApprovedException extends RuntimeException {

    public LoanAlreadyApprovedException() {
        super(Code.LOAN_ALREADY_APPROVED.getMessage());
    }

}
