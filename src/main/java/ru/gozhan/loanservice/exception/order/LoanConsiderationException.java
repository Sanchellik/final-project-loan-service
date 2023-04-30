package ru.gozhan.loanservice.exception.order;

import ru.gozhan.loanservice.constant.Code;

public class LoanConsiderationException extends RuntimeException {

    public LoanConsiderationException() {
        super(Code.LOAN_CONSIDERATION.getMessage());
    }

}
