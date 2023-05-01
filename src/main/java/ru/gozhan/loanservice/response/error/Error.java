package ru.gozhan.loanservice.response.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.gozhan.loanservice.constant.Code;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Code code;
    private String message;

    @JsonCreator
    public Error(@JsonProperty("code") Code code, @JsonProperty("message") String message) {
        this.code = code;
        this.message = message;
    }
}
