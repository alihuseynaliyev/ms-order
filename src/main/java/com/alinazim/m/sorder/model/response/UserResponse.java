package com.alinazim.m.sorder.model.response;

import com.alinazim.m.sorder.model.enums.OrderStatus;
import com.alinazim.m.sorder.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long orderId;
    private Long userId;
    private Long productId;
    private OrderStatus orderStatus;
    private ProductType productType;
    Object details;
}
