package com.chotchip.subTrack.entity;

import com.chotchip.subTrack.entity.enumurated.PaymentMethod;
import com.chotchip.subTrack.entity.enumurated.ServiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Data
@EqualsAndHashCode(of = "id")
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private Double price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private UUID userId;


}
