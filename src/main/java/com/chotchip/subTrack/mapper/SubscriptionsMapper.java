package com.chotchip.subTrack.mapper;

import com.chotchip.subTrack.dto.CreateSubscriptionsDTO;
import com.chotchip.subTrack.entity.Subscriptions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface SubscriptionsMapper {
    @Mapping(target = "startDate", expression = "java(getCurrentDateTime())")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "endDate", expression = "java(getCurrentDateTimePlus30Days())")
    Subscriptions toEntity(CreateSubscriptionsDTO createSubscriptionsDTO);

    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    default LocalDateTime getCurrentDateTimePlus30Days() {
        return LocalDateTime.now().plusDays(30);
    }
}
