package com.alinazim.m.sorder.mapper.factory;

import com.alinazim.m.sorder.dao.entity.OrderEntity;
import com.alinazim.m.sorder.model.response.ProductResponse;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public ProductResponse buildProductResponse(OrderEntity order) {
        return ProductResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .productType(order.getProductType())
                .details(order.getDetails())
                .build();
    }
}
