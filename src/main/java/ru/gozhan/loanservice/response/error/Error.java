package ru.gozhan.loanservice.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.gozhan.loanservice.constant.Code;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Code code;
    private String message;

}
