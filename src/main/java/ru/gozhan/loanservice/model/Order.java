package ru.gozhan.loanservice.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Order {

    private Long id;

    private UUID orderId;

    private Long userId;

    private Long tariffId;

    private Double creditRating;

    private String status;

    private Timestamp timeInsert;

    private Timestamp timeUpdate;

}
