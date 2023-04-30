package ru.gozhan.loanservice.response.error;

import lombok.Builder;
import lombok.Data;
import ru.gozhan.loanservice.response.Response;

@Builder
@Data
public class ErrorResponse implements Response {

   private Error error;

}
