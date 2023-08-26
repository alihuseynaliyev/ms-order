package com.alinazim.m.sorder.mapper.factory;

import com.alinazim.m.sorder.dao.entity.OrderEntity;
import com.alinazim.m.sorder.model.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public enum UserMapper {
    USER_MAPPER;

    @SneakyThrows
    public UserResponse buildUserResponse(OrderEntity order){
        return UserResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .orderStatus(order.getOrderStatus())
                .productType(order.getProductType())
                .details(new ObjectMapper().readValue(order.getDetails(), Object.class))
                .build();
    }
}
