package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("order")
    public ResponseEntity<Response> makeOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        log.info("/order START");

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderService.createOrder(createOrderRequest.getUserId(), createOrderRequest.getTariffId()))
                .build();

        SuccessResponse<OrderResponse> successResponse = SuccessResponse.<OrderResponse>builder()
                .data(orderResponse)
                .build();

        log.info("/order FINISH");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("getStatusOrder")
    public ResponseEntity<Response> getOrderStatus(@RequestParam("orderId") UUID orderId) {

        log.info("/getStatusOrder START");
        OrderStatusResponse orderStatusResponse = OrderStatusResponse.builder()
                .orderStatus(orderService.getOrderStatus(orderId))
                .build();

        SuccessResponse<OrderStatusResponse> successResponse = SuccessResponse.<OrderStatusResponse>builder()
                .data(orderStatusResponse)
                .build();

        log.info("/getStatusOrder FINISH");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("deleteOrder")
    public ResponseEntity<Response> deleteOrder(@RequestBody DeleteOrderRequest deleteOrderRequest) {

        log.info("/deleteOrder START");
        orderService.deleteOrder(deleteOrderRequest.getUserId(), deleteOrderRequest.getOrderId());

        log.info("/deleteOrder FINISH");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
