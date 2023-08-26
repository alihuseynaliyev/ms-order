package com.alinazim.m.sorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-ticket", url = "http://localhost:8082/v1/tickets")
public interface TicketClient {
    @PostMapping("/{orderId}")
    void createTicket(@PathVariable Long orderId);

}
