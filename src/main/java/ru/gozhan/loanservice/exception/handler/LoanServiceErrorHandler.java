package ru.gozhan.loanservice.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import ru.gozhan.loanservice.constant.ErrorCode;
import ru.gozhan.loanservice.exception.base.BaseException;
import ru.gozhan.loanservice.exception.order.*;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;
import ru.gozhan.loanservice.response.error.Error;
import ru.gozhan.loanservice.response.error.ErrorResponse;

@ControllerAdvice //TODO check, maybe RestControllerAdvice
@Slf4j
public class LoanServiceErrorHandler {

//    @ExceptionHandler({LoanConsiderationException.class, LoanAlreadyApprovedException.class,
//            OrderImpossibleToDeleteException.class, OrderNotFoundException.class, //TODO maybe delete this from here
//            TryLaterException.class, TariffNotFoundException.class})
//    public ResponseEntity<ErrorResponse> handleLoanExceptions(Exception ex) {
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .error(
//                        Error.builder()
//                                .errorCode(ex instanceof LoanConsiderationException ? ErrorCode.LOAN_CONSIDERATION :
//                                        ex instanceof LoanAlreadyApprovedException ? ErrorCode.LOAN_ALREADY_APPROVED :
//                                                ex instanceof TryLaterException ? ErrorCode.TRY_LATER :
//                                                        ex instanceof TariffNotFoundException ? ErrorCode.TARIFF_NOT_FOUND :
//                                                                ex instanceof OrderNotFoundException ? ErrorCode.ORDER_NOT_FOUND :
//                                                                        ex instanceof OrderImpossibleToDeleteException ? ErrorCode.ORDER_IMPOSSIBLE_TO_DELETE :
//                                                                                null
//                                )
//                                .message(ex.getMessage()).build()
//                ).build();
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BaseException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(Error.builder()
                        .code(ex.getCode())
                        .message(ex.getCode().getMessage())
                        .build())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class) //TODO try to create mapper fromJsonToResponse in util package
    public String handleHttpClientErrorException(HttpClientErrorException.BadRequest ex, Model model) {

        String responseBody = ex.getResponseBodyAsString();
        log.info("Here is ex.getResponseBodyAsString: {}", responseBody); //TODO delete log
        ObjectMapper objectMapper = new ObjectMapper();

        try {
//            ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
//            log.info("ErrorResponse {}", errorResponse);
//
//            model.addAttribute("error", errorResponse.getError());
            ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);

            model.addAttribute("error", errorResponse.getError());

        } catch (JsonProcessingException e) {

            e.printStackTrace();

            Error error = Error.builder()
                    .code(ErrorCode.UNKNOWN_ERROR)
                    .message(ErrorCode.UNKNOWN_ERROR.getMessage())
                    .build();
            model.addAttribute("error", error);
        }

        return "created-order";
    }

}
