package ru.gozhan.loanservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.gozhan.loanservice.dto.OrderDto;
import ru.gozhan.loanservice.model.Order;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "timeInsert", target = "timeInsert")
    @Mapping(source = "timeUpdate", target = "timeUpdate")
    OrderDto toDto(Order order);

    List<OrderDto> toDtoList(List<Order> orders);

}
