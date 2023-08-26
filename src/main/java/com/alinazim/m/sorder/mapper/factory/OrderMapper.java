package com.alinazim.m.sorder.mapper.factory;

import com.alinazim.m.sorder.dao.entity.OrderEntity;
import com.alinazim.m.sorder.model.client.response.AuthResponse;
import com.alinazim.m.sorder.model.request.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.alinazim.m.sorder.model.enums.OrderStatus.PENDING;
import static com.alinazim.m.sorder.model.enums.ProductType.CARD;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity buildOrderEntity(OrderRequest<?> order, AuthResponse userId) throws JsonProcessingException {
        return OrderEntity.builder()
                .userId(userId.getUserId())
                .orderStatus(PENDING)
                .details(new ObjectMapper().writeValueAsString(order.getDetails()))
                .productType(CARD)
                .build();
    }
}
