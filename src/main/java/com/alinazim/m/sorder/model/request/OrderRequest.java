package com.alinazim.m.sorder.model.request;

import com.alinazim.m.sorder.model.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest<T> {
    private ProductType productType;
    private T details;
}
