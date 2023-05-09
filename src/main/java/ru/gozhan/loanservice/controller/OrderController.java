package ru.gozhan.loanservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.gozhan.loanservice.dto.OrderDto;
import ru.gozhan.loanservice.request.CreateOrderRequest;
import ru.gozhan.loanservice.request.DeleteOrderRequest;
import ru.gozhan.loanservice.response.base.Response;
import ru.gozhan.loanservice.response.success.SuccessResponse;
import ru.gozhan.loanservice.response.success.order.OrderResponse;
import ru.gozhan.loanservice.service.OrderService;
import ru.gozhan.loanservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/loan-service")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final UserService userService;

    private final OrderService orderService;

    @PostMapping("makeOrder/{tariffId}")
    public String createOrder(@PathVariable Long tariffId, Model model) {

        RestTemplate restTemplate = new RestTemplate();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByUsername(username);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest(userId, tariffId);
        String url = "http://localhost:8080/loan-service/order";

        HttpEntity<CreateOrderRequest> requestEntity = new HttpEntity<>(createOrderRequest);
        ResponseEntity<SuccessResponse<OrderResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        SuccessResponse<OrderResponse> successResponse = response.getBody();
        assert successResponse != null;
        model.addAttribute("data", successResponse.getData());

        return "created-order";
    }

    @GetMapping("user/orders")
    public String showOrders(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<OrderDto> userOrders = orderService.getUserOrders(
                userService.getUserIdByUsername(username)
        );

        model.addAttribute("orders", userOrders);
        model.addAttribute("username", username);
        return "user-orders";
    }

    @PostMapping("deleteOrder/{username}/{orderId}")
    public String deleteOrder(@PathVariable("username") String username,
                              @PathVariable("orderId") UUID orderId,
                              Model model) {

        RestTemplate restTemplate = new RestTemplate();

        Long userId = userService.getUserIdByUsername(username);

        DeleteOrderRequest deleteOrderRequest = new DeleteOrderRequest(userId, orderId);
        String url = "http://localhost:8080/loan-service/deleteOrder";

        HttpEntity<DeleteOrderRequest> requestEntity = new HttpEntity<>(deleteOrderRequest);

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                Response.class
        );

        model.addAttribute("orderId", orderId);

        return "deleted-order";
    }

}
