package ru.gozhan.loanservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gozhan.loanservice.exception.order.LoanAlreadyApprovedException;
import ru.gozhan.loanservice.exception.order.LoanConsiderationException;
import ru.gozhan.loanservice.exception.order.TryLaterException;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;
import ru.gozhan.loanservice.request.OrderRequest;
import ru.gozhan.loanservice.response.Response;
import ru.gozhan.loanservice.response.SuccessResponse;
import ru.gozhan.loanservice.response.order.OrderResponse;
import ru.gozhan.loanservice.service.OrderService;

@RestController
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Response> makeOrder(@RequestBody OrderRequest orderRequest)
            throws TariffNotFoundException, LoanConsiderationException,
            LoanAlreadyApprovedException, TryLaterException {

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderService.tariffProcessing(orderRequest.getUserId(), orderRequest.getTariffId()))
                .build();

        SuccessResponse<OrderResponse> successResponse = SuccessResponse.<OrderResponse>builder()
                .data(orderResponse)
                .build();

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

}
