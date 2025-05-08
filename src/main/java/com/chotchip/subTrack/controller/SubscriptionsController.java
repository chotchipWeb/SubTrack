package com.chotchip.subTrack.controller;

import com.chotchip.subTrack.dto.CreateSubscriptionsDTO;
import com.chotchip.subTrack.dto.CreateUserDTO;
import com.chotchip.subTrack.entity.Subscriptions;
import com.chotchip.subTrack.entity.User;
import com.chotchip.subTrack.entity.enumurated.ServiceType;
import com.chotchip.subTrack.service.SubscriptionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/{userId}/subscriptions")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Подписки", description = "Операции с подписками")
public class SubscriptionsController {

    private final SubscriptionsService subService;

    @PostMapping
    @Operation(
            summary = "Создание подписки",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "id пользователя",
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            required = true,
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Тело запроса, содержащее основные данные для создания подписки",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateSubscriptionsDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Возвращается созданная подписка",
                            content = @Content(
                                    schema = @Schema(implementation = Subscriptions.class)
                            ))
            }
    )
    public ResponseEntity<Subscriptions> save(@PathVariable("userId") UUID userId, @RequestBody CreateSubscriptionsDTO sub) {
        log.info("Rest request to create subscriptions: {}", sub);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        subService.addSubToUser(userId, sub)
                );
    }

    @GetMapping
    @Operation(
            summary = "Получение всех подписок у пользователя",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "id пользователя",
                            required = true,
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Возвращается список подписок у пользователя",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Subscriptions.class)
                                    )
                            ))
            }
    )
    public ResponseEntity<List<Subscriptions>> getAllUserSub(@PathVariable("userId") UUID userId) {
        log.info("Rest request get all subscriptions of a user by user ID: {}", userId);
        return ResponseEntity.ok(
                subService.getAllSubByUserId(userId)
        );
    }

    @DeleteMapping("/{subId}")
    @Operation(
            summary = "Удаление подписки",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "id пользователя",
                            required = true,
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    ),
                    @Parameter(
                            name = "subId",
                            description = "id подписки",
                            required = true,
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Возвращается статус код успешной операции")
            }
    )
    public ResponseEntity<Void> deleteSub(@PathVariable("userId") UUID userId, @PathVariable("subId") UUID subId) {
        log.info("Rest request to delete subscriptions: {}", subId);
        subService.deleteSub(userId, subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top")
    @Operation(
            summary = "Получение топ 3 подписок",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "id пользователя",
                            required = true,
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Возвращается список сервисных типов",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ServiceType.class)
                                    )
                            ))
            }
    )
    public ResponseEntity<List<ServiceType>> getTopSubscriptions(@PathVariable("userId") UUID userId) {
        log.info("Rest request to get top subscriptions");
        return ResponseEntity.ok(
                subService.getTopSubscriptions(userId)
        );
    }
}
