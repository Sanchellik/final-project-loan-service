package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gozhan.loanservice.request.CreateOrderRequest;
import ru.gozhan.loanservice.request.DeleteOrderRequest;
import ru.gozhan.loanservice.response.base.Response;
import ru.gozhan.loanservice.response.success.SuccessResponse;
import ru.gozhan.loanservice.response.success.order.OrderResponse;
import ru.gozhan.loanservice.response.success.order.OrderStatusResponse;
import ru.gozhan.loanservice.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Response> makeOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderService.orderProcessing(createOrderRequest.getUserId(), createOrderRequest.getTariffId()))
                .build();

        SuccessResponse<OrderResponse> successResponse = SuccessResponse.<OrderResponse>builder()
                .data(orderResponse)
                .build();

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("/getStatusOrder")
    public ResponseEntity<Response> getOrderStatus(@RequestParam("orderId") UUID orderId) {

        OrderStatusResponse orderStatusResponse = OrderStatusResponse.builder()
                .orderStatus(orderService.getOrderStatus(orderId))
                .build();

        SuccessResponse<OrderStatusResponse> successResponse = SuccessResponse.<OrderStatusResponse>builder()
                .data(orderStatusResponse)
                .build();

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Response> deleteOrder(@RequestBody DeleteOrderRequest deleteOrderRequest) {

        orderService.deleteOrder(deleteOrderRequest.getUserId(), deleteOrderRequest.getOrderId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
