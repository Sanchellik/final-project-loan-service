package ru.gozhan.loanservice.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    LOAN_CONSIDERATION("Заявка находится на рассмотрении"),
    LOAN_ALREADY_APPROVED("Заявка уже одобрена"),
    TRY_LATER("Попробуйте позже"),
    TARIFF_NOT_FOUND("Тариф не найден"),

    ORDER_NOT_FOUND("Заявка не найдена"),
    ORDER_IMPOSSIBLE_TO_DELETE("Невозможно удалить заявку"),

    UNKNOWN_ERROR("Неизвестная ошибка");

    private final String message;

}
