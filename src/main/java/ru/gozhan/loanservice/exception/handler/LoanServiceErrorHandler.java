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
import ru.gozhan.loanservice.response.error.Error;
import ru.gozhan.loanservice.response.error.ErrorResponse;

@ControllerAdvice //TODO check, maybe RestControllerAdvice
@Slf4j
public class LoanServiceErrorHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {

        log.info("handleBaseException START");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(Error.builder()
                        .code(ex.getCode())
                        .message(ex.getCode().getMessage())
                        .build())
                .build();

        log.info("handleBaseException FINISH");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public String handleHttpClientErrorException(HttpClientErrorException.BadRequest ex, Model model) {

        log.info("handleHttpClientErrorException START");
        String responseBody = ex.getResponseBodyAsString();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
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

        log.info("handleHttpClientErrorException FINISH");
        return "created-order";
    }

}
