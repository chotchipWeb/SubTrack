package com.chotchip.subTrack.controller;

import com.chotchip.subTrack.dto.CreateUserDTO;
import com.chotchip.subTrack.dto.UpdateUserDTO;
import com.chotchip.subTrack.entity.User;
import com.chotchip.subTrack.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователи", description = "Операции с пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Создание пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Тело запроса, содержащее email пользователя и пароль",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateUserDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Возвращается созданный пользователь",
                            content = @Content(
                                    schema = @Schema(implementation = User.class)
                            ))
            }
    )
    public ResponseEntity<User> save(@RequestBody CreateUserDTO user) {
        log.info("Rest request to create user: {}", user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        userService.save(user)
                );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение пользователя по id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "id пользователя",
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            required = true,
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Возвращается найденный пользователь",
                            content = @Content(
                                    schema = @Schema(implementation = User.class)
                            ))
            }
    )
    public ResponseEntity<User> findById(@PathVariable("id") UUID id) {
        log.info("Rest request find user by id: {}", id);
        return ResponseEntity
                .ok(
                        userService.getUserById(id)
                );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление пользователя по id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "id пользователя",
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            required = true,
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Тело запроса, содержащее email пользователя и пароль для изменения",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Возвращается обновленный пользователь",
                            content = @Content(
                                    schema = @Schema(implementation = User.class)
                            ))
            }
    )
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody UpdateUserDTO user) {
        log.info("Rest request update user: {} with id: {}", user, id);
        return ResponseEntity
                .ok(
                        userService.updateUser(id, user)
                );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление пользователя по id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "id пользователя",
                            example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                            required = true,
                            schema = @Schema(type = "String", example = "13a4ee42-e919-42c7-b604-3ce0363fb398",
                                    implementation = UUID.class)
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Возвращается статус код успешной операции")
            }
    )
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        log.info("Rest request delete user by id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
