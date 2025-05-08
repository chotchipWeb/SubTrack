package com.chotchip.subTrack.dto;

import com.chotchip.subTrack.entity.enumurated.PaymentMethod;
import com.chotchip.subTrack.entity.enumurated.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для создания подписки")
public record CreateSubscriptionsDTO(
        @Schema(description = "Тип подписки", example = "VK_MUSIC")
        ServiceType serviceType,
        @Schema(description = "Цена подписки", example = "123")
        Double price,
        @Schema(description = "Тип волюты", example = "RUB")
        String currency,
        @Schema(description = "Тип оплаты", example = "CARD")
        PaymentMethod paymentMethod) {
}
