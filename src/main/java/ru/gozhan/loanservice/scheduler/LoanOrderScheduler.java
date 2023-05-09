package ru.gozhan.loanservice.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.service.OrderService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoanOrderScheduler {

    private final OrderService orderService;

    @Scheduled(fixedRate = 120000)
    public void acceptOrRejectOrder() {

        log.info("JOB START");
        List<Order> orders = orderService.getOrdersByStatus("IN_PROGRESS");

        Random random = new Random();
        for (Order order : orders) {
            boolean approved = random.nextBoolean();
            String newStatus = approved ? "APPROVED" : "REFUSED";
            order.setStatus(newStatus);

            Timestamp timeUpdate = new Timestamp(System.currentTimeMillis());
            order.setTimeUpdate(timeUpdate);

            orderService.updateStatusAndTime(order);
        }
        log.info("JOB FINISH");
    }

}
