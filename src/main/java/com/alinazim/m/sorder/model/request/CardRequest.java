package com.alinazim.m.sorder.model.request;

import com.alinazim.m.sorder.model.enums.CardType;
import lombok.Data;

@Data
public class CardRequest {
    private String fullName;
    private CardType cardType;
    private String expireTime;
}
