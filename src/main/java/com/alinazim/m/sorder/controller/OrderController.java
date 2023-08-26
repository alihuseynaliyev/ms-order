package com.alinazim.m.sorder.controller;

import com.alinazim.m.sorder.model.request.OrderRequest;
import com.alinazim.m.sorder.model.response.ProductResponse;
import com.alinazim.m.sorder.model.response.UserResponse;
import com.alinazim.m.sorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderRequest<?> order,
                            @RequestHeader(name = "Authorization") String token) {
        orderService.createOrder(order, token);
    }

    @GetMapping
    public List<UserResponse> getOrders(@RequestHeader(name = "Authorization") String token ) {
        return orderService.getOrders(token);
    }

    @GetMapping("/{orderId}")
    public ProductResponse getOrder(@PathVariable Long orderId){
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}/products/{productId}")
    public void updateOrder(@PathVariable Long orderId, @PathVariable Long productId){
        orderService.updateOrder(orderId, productId);
    }

}