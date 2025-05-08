package com.chotchip.subTrack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для обновления пользователя")
public record UpdateUserDTO(
        @Schema(description = "Почта пользователя", example = "1234@gmail.com")
        String email,
        @Schema(description = "Пароль пользователя", example = "qwerty")
        String password) {
}
