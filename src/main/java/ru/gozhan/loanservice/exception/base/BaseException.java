package ru.gozhan.loanservice.exception.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.gozhan.loanservice.constant.ErrorCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private ErrorCode code;

    public BaseException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

}
