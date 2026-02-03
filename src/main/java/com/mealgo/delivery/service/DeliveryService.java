package com.mealgo.delivery.service;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.delivery.domain.entity.DeliveryHistory;
import com.mealgo.delivery.repository.DeliveryHistoryRepository;
import com.mealgo.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private DeliveryRepository deliveryRepository;
    private DeliveryHistoryRepository deliveryHistoryRepository;


    public void updateStatusIfDue(Delivery delivery) {
        LocalDateTime now = LocalDateTime.now();

        if (delivery.getDeliStat() == DeliveryEnum.DELIVERY_READY &&
                delivery.getDeliDt().plusMinutes(30).isBefore(now)) {
            delivery.setDeliStat(DeliveryEnum.DELIVERY_ONGOING);
            deliveryRepository.save(delivery);
            saveHistory(delivery, DeliveryEnum.DELIVERY_ONGOING);
        } else if (delivery.getDeliStat() == DeliveryEnum.DELIVERY_ONGOING &&
                delivery.getDeliDt().plusMinutes(60).isBefore(now)) {
            delivery.setDeliStat(DeliveryEnum.DELIVERY_COMPLETE);
            deliveryRepository.save(delivery);
            saveHistory(delivery, DeliveryEnum.DELIVERY_COMPLETE);
        }
    }

    private void saveHistory(Delivery delivery, DeliveryEnum status) {
        DeliveryHistory history = DeliveryHistory.builder()
                .delivery(delivery)
                .dhStat(status)
                .build();
        deliveryHistoryRepository.save(history);
    }


}
