package ru.gozhan.loanservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gozhan.loanservice.constant.Code;
import ru.gozhan.loanservice.exception.order.*;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;
import ru.gozhan.loanservice.response.error.Error;
import ru.gozhan.loanservice.response.error.ErrorResponse;

@ControllerAdvice //TODO check, maybe RestControllerAdvice
public class LoanServiceErrorHandler {

//    @ExceptionHandler({TariffNotFoundException.class, LoanConsiderationException.class,
//            LoanAlreadyApprovedException.class, TryLaterException.class})
//    public ResponseEntity<ErrorResponse> handleException(Exception e) {
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .error(Error.builder().code(Code.valueOf(e.getClass().getSimpleName())).message(e.getMessage()).build())
//                .build();
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler({LoanConsiderationException.class, LoanAlreadyApprovedException.class,
            OrderImpossibleToDeleteException.class, OrderNotFoundException.class,
            TryLaterException.class, TariffNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleLoanConsideration(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(
                        Error.builder()
                                .code(ex instanceof LoanConsiderationException ? Code.LOAN_CONSIDERATION :
                                        ex instanceof LoanAlreadyApprovedException ? Code.LOAN_ALREADY_APPROVED :
                                                ex instanceof TryLaterException ? Code.TRY_LATER :
                                                        ex instanceof TariffNotFoundException ? Code.TARIFF_NOT_FOUND :
                                                                ex instanceof OrderNotFoundException ? Code.ORDER_NOT_FOUND :
                                                                        ex instanceof OrderImpossibleToDeleteException ? Code.ORDER_IMPOSSIBLE_TO_DELETE :
                                                                                null
                                )
                                .message(ex.getMessage()).build()
                ).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
