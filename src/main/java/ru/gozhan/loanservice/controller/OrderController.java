package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.gozhan.loanservice.request.OrderRequest;
import ru.gozhan.loanservice.response.success.SuccessResponse;
import ru.gozhan.loanservice.response.success.order.OrderResponse;
import ru.gozhan.loanservice.service.UserService;

@Controller
@RequestMapping("/loan-service")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;

    @PostMapping("makeOrder/{tariffId}")
    public String createOrder(@PathVariable String tariffId, Model model) {

        RestTemplate restTemplate = new RestTemplate();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByUsername(username);

        OrderRequest orderRequest = new OrderRequest(userId, Long.parseLong(tariffId));
        String url = "http://localhost:8080/loan-service/order";

        HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(orderRequest);
        ResponseEntity<SuccessResponse<OrderResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        SuccessResponse<OrderResponse> successResponse = response.getBody();
        model.addAttribute("data", successResponse.getData());

        return "created-order";

//        RestTemplate restTemplate = new RestTemplate();
//
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Long userId = userService.getUserIdByUsername(username);
//
//        OrderRequest orderRequest = new OrderRequest(userId, Long.parseLong(tariffId));
//        String url = "http://localhost:8080/loan-service/order";
//
//        HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(orderRequest);
//        ResponseEntity<Response> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                requestEntity,
//                new ParameterizedTypeReference<Response>() {}
//        );
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() instanceof SuccessResponse) {
//
//            SuccessResponse<OrderResponse> successResponse = (SuccessResponse<OrderResponse>) responseEntity.getBody();
//            model.addAttribute("data", successResponse.getData());
//
//        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST
//                && responseEntity.getBody() instanceof ErrorResponse) {
//
//            ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
//            model.addAttribute("error", errorResponse.getError());
//
//        } else {
//            Error customError = Error.builder()
//                    .code(Code.UNKNOWN_ERROR)
//                    .message("Неизвестная ошибка")
//                    .build();
//            model.addAttribute("error", customError);
//        }
//
//        return "created-order";
    }

}
