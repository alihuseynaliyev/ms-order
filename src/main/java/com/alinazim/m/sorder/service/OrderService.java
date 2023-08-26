package com.alinazim.m.sorder.service;

import com.alinazim.m.sorder.client.AuthClient;
import com.alinazim.m.sorder.client.TicketClient;
import com.alinazim.m.sorder.dao.entity.OrderEntity;
import com.alinazim.m.sorder.dao.repository.OrderRepository;
import com.alinazim.m.sorder.mapper.factory.OrderMapper;
import com.alinazim.m.sorder.mapper.factory.ProductMapper;
import com.alinazim.m.sorder.mapper.factory.UserMapper;
import com.alinazim.m.sorder.model.client.response.AuthResponse;
import com.alinazim.m.sorder.model.request.OrderRequest;
import com.alinazim.m.sorder.model.response.ProductResponse;
import com.alinazim.m.sorder.model.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alinazim.m.sorder.mapper.factory.OrderMapper.*;
import static com.alinazim.m.sorder.mapper.factory.ProductMapper.PRODUCT_MAPPER;
import static com.alinazim.m.sorder.mapper.factory.ProductMapper.valueOf;
import static com.alinazim.m.sorder.mapper.factory.UserMapper.USER_MAPPER;
import static com.alinazim.m.sorder.model.enums.OrderStatus.PENDING;
import static com.alinazim.m.sorder.model.enums.ProductType.CARD;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AuthClient authClient;
    private final TicketClient ticketClient;

    @SneakyThrows
    public void createOrder(OrderRequest<?> order, String token) {
        String jwtToken = token.replace("Bearer ", "");
        if (!authClient.verify(jwtToken)) throw new RuntimeException("Token is not correct");

        var orderEntity = ORDER_MAPPER.buildOrderEntity(order, authClient.getUsers(jwtToken));

        orderRepository.save(orderEntity);

        ticketClient.createTicket(orderEntity.getId());
    }



    @SneakyThrows
    public ProductResponse getOrder(Long orderId){
        var order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order does not exist"));
        return PRODUCT_MAPPER.buildProductResponse(order);
    }



    public List<UserResponse> getOrders(String token) {
        var jwtToken = token.replace("Bearer ", "");
        if (!authClient.verify(jwtToken)) throw new RuntimeException("Token is not correct");

        return orderRepository.findByUserId(authClient.getUsers(jwtToken).getUserId())
                .stream()
                .map(USER_MAPPER::buildUserResponse)
                .collect(toList());
    }




    public void updateOrder(Long orderId, Long productId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException(""));
        order.setProductId(productId);
        orderRepository.save(order);
    }
}
