package ru.gozhan.loanservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID orderId;
    private String status;

    private Timestamp timeInsert;
    private Timestamp timeUpdate;

}
