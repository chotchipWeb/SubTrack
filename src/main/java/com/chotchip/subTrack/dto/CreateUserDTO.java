package com.chotchip.subTrack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для создания пользователя")
public record CreateUserDTO(
        @Schema(description = "Email пользователя", example = "example@gmail.com")
        String email,
        @Schema(description = "Пароль пользователя", example = "123456")
        String password) {

}
