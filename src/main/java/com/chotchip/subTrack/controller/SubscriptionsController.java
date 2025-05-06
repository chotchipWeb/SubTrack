package com.chotchip.subTrack.controller;

import com.chotchip.subTrack.dto.CreateSubscriptionsDTO;
import com.chotchip.subTrack.entity.Subscriptions;
import com.chotchip.subTrack.service.SubscriptionsService;
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
public class SubscriptionsController {

    private final SubscriptionsService subService;

    @PostMapping
    public ResponseEntity<Subscriptions> save(@PathVariable("userId") UUID userId, @RequestBody CreateSubscriptionsDTO sub) {
        log.info("Rest request to create subscriptions: {}", sub);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        subService.addSubToUser(userId, sub)
                );
    }

    @GetMapping
    public ResponseEntity<List<Subscriptions>> getAllUserSub(@PathVariable("userId") UUID userId) {
        log.info("Rest request get all subscriptions of a user by user ID: {}", userId);
        return ResponseEntity.ok(
                subService.getAllSubByUserId(userId)
        );
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Void> deleteSub(@PathVariable("userId") UUID userId, @PathVariable("subId") UUID subId) {
        log.info("Rest request to delete subscriptions: {}", subId);
        subService.deleteSub(userId, subId);
        return ResponseEntity.noContent().build();
    }


}
