package com.chotchip.subTrack.dto;

import com.chotchip.subTrack.entity.enumurated.PaymentMethod;
import com.chotchip.subTrack.entity.enumurated.ServiceType;

import java.util.UUID;

public record CreateSubscriptionsDTO(ServiceType serviceType, Double price, String currency,
                                     PaymentMethod paymentMethod, UUID userId) {
}
