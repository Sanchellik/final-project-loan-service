package ru.gozhan.loanservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {
//    LOAN_CONSIDERATION,
//    LOAN_ALREADY_APPROVED,
//    TRY_LATER,
//    TARIFF_NOT_FOUND,
//
//    ORDER_NOT_FOUND,
//    ORDER_IMPOSSIBLE_TO_DELETE

    LOAN_CONSIDERATION("Заявка находится на рассмотрении"),
    LOAN_ALREADY_APPROVED("Заявка уже одобрена"),
    TRY_LATER("Попробуйте позже"),
    TARIFF_NOT_FOUND("Тариф не найден"),

    ORDER_NOT_FOUND("Заявка не найдена"),
    ORDER_IMPOSSIBLE_TO_DELETE("Невозможно удалить заявку");

    private final String message;

}
