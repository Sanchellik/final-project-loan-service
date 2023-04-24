package ru.gozhan.loanservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tariff {

    private Long id;

    private String type;

    @JsonProperty("interest_rate")
    private String interestRate;

}
