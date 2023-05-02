package ru.gozhan.loanservice.response.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gozhan.loanservice.response.base.Response;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Response {

   private Error error;

}
