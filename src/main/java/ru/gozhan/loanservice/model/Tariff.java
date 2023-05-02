package ru.gozhan.loanservice.model;

import lombok.Data;

@Data
public class Tariff {

    private Long id;

    private String type;

    private String interestRate;

}
