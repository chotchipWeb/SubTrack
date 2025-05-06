package com.chotchip.subTrack.repository;

import com.chotchip.subTrack.entity.Subscriptions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, UUID> {

    List<Subscriptions> findByUserId(UUID id);

    @Query(
            value = """
                    SELECT 
                        s.service_name as serviceName, 
                        COUNT(*) as subscriptionCount 
                    FROM 
                        subscriptions s
                    WHERE 
                        s.is_active = TRUE
                    GROUP BY 
                        s.service_name
                    ORDER BY 
                        COUNT(*) DESC
                    LIMIT 3
                    """,
            nativeQuery = true
    )
    List<Subscriptions> findTop3ActiveSubscriptions(Pageable pageable);
}
