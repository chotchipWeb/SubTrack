package com.chotchip.subTrack.entity.enumurated;

import lombok.Getter;

@Getter
public enum ServiceType {
    YOUTUBE_PREMIUM("YOUTUBE_PREMIUM"),
    VK_MUSIC("VK_MUSIC"),
    YANDEX_PLUS("YANDEX_PLUS"),
    NETFLIX("NETFLIX");
    private final String value;

    ServiceType(String value) {
        this.value = value;
    }
}
