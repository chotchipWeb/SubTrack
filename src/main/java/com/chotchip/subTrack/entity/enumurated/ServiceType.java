package com.chotchip.subTrack.entity.enumurated;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Тип сервисов")
public enum ServiceType {
    YOUTUBE_PREMIUM("YOUTUBE_PREMIUM"),
    VK_MUSIC("VK_MUSIC"),
    YANDEX_PLUS("YANDEX_PLUS"),
    NETFLIX("NETFLIX");
    @Schema(description = "Тип сервисов",
            allowableValues = {"YOUTUBE_PREMIUM", "VK_MUSIC", "YANDEX_PLUS", "NETFLIX"}
    )
    private final String value;

    ServiceType(String value) {
        this.value = value;
    }
}
