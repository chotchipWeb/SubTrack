package com.chotchip.subTrack.entity.enumurated;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CARD("CARD");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }
}
