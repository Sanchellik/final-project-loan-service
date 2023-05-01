package ru.gozhan.loanservice.response.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.gozhan.loanservice.response.Response;

@Builder
@Data
public class ErrorResponse implements Response {

   private Error error;

   @JsonCreator
   public ErrorResponse(@JsonProperty("error") Error error) {
      this.error = error;
   }

}
