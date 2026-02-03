package com.mealgo.delivery.scheduler;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.delivery.repository.DeliveryRepository;
import com.mealgo.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveryScheduler {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryService deliveryService;

    @Scheduled(fixedRate = 1000 * 60 * 5) // 5분마다
    public void checkAndUpdateDeliveryStatus() {
        List<DeliveryEnum> targetStatuses = List.of(
                DeliveryEnum.DELIVERY_READY,
                DeliveryEnum.DELIVERY_ONGOING
        );

        List<Delivery> deliveries = deliveryRepository.findByDeliStatIn(targetStatuses);

        for (Delivery delivery : deliveries) {
            deliveryService.updateStatusIfDue(delivery);
        }
    }

}


