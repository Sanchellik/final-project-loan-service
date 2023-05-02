package ru.gozhan.loanservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import ru.gozhan.loanservice.constant.Code;
import ru.gozhan.loanservice.exception.order.*;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;
import ru.gozhan.loanservice.response.error.Error;
import ru.gozhan.loanservice.response.error.ErrorResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@ControllerAdvice //TODO check, maybe RestControllerAdvice
@Slf4j
public class LoanServiceErrorHandler {

    @ExceptionHandler({LoanConsiderationException.class, LoanAlreadyApprovedException.class,
            OrderImpossibleToDeleteException.class, OrderNotFoundException.class, //TODO maybe delete this from here
            TryLaterException.class, TariffNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleLoanExceptions(Exception ex) {

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

    @ExceptionHandler(HttpClientErrorException.class) //TODO try to create mapper fromJsonToResponse in util package
    public String handleHttpClientErrorException(HttpClientErrorException ex, Model model) {

        String responseBody = ex.getResponseBodyAsString();
        log.info("Here is ex.getResponseBodyAsString: {}", responseBody); //TODO delete log
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
            log.info("ErrorResponse {}", errorResponse);

            model.addAttribute("error", errorResponse.getError());

        } catch (JsonProcessingException e) {

            e.printStackTrace();

            Error error = Error.builder()
                    .code(Code.UNKNOWN_ERROR)
                    .message(Code.UNKNOWN_ERROR.getMessage())
                    .build();
            model.addAttribute("error", error);
        }

        return "created-order";
    }

}
