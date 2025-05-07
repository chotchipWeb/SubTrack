package com.chotchip.subTrack.service;

import com.chotchip.subTrack.dto.CreateSubscriptionsDTO;
import com.chotchip.subTrack.entity.Subscriptions;
import com.chotchip.subTrack.entity.enumurated.ServiceType;
import com.chotchip.subTrack.mapper.SubscriptionsMapper;
import com.chotchip.subTrack.repository.SubscriptionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionsService {

    private final SubscriptionsRepository subRepository;
    private final SubscriptionsMapper mapper;

    @Transactional
    public Subscriptions addSubToUser(UUID userId, CreateSubscriptionsDTO subscriptionsDTO) {
        Subscriptions subscriptions = mapper.toEntity(subscriptionsDTO);
        subscriptions.setUserId(userId);
        Subscriptions save = subRepository.save(subscriptions);
        log.info("Saved subscriptions: {} for user ID: {}", save, userId);
        return save;
    }

    @Transactional(readOnly = true)
    public List<Subscriptions> getAllSubByUserId(UUID userId) {
        List<Subscriptions> subscriptionsByUserId = subRepository.findByUserId(userId);
        log.info("All subscriptions: {} by user ID: {}", subscriptionsByUserId, userId);
        return subscriptionsByUserId;
    }

    @Transactional
    public void deleteSub(UUID userId, UUID subId) {
        subRepository.deleteById(subId);
        log.info("Delete subscriptions by ID: {} from the user ID: {}", subId, userId);
    }

    @Transactional(readOnly = true)
    public List<ServiceType> getTopSubscriptions(UUID userId) {
        List<ServiceType> top3ActiveSubscriptions = subRepository.findTop3ActiveSubscriptions();
        log.info("Get top subscriptions: {} from the user ID: {}", top3ActiveSubscriptions, userId);
        return top3ActiveSubscriptions;
    }
}
