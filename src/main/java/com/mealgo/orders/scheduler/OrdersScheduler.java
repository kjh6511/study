package com.mealgo.orders.scheduler;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.common.code.OrderEnum;
import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.delivery.domain.entity.DeliveryHistory;
import com.mealgo.delivery.repository.DeliveryHistoryRepository;
import com.mealgo.delivery.repository.DeliveryRepository;
import com.mealgo.orders.domain.entity.Orders;
import com.mealgo.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersScheduler {

    private final OrdersRepository ordersRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryHistoryRepository deliveryHistoryRepository;

    @Transactional
    @Scheduled(fixedRate = 60_000) // 1분마다 체크
    public void updateOrderStatusToConfirm() {
        LocalDateTime now = LocalDateTime.now();

        List<Orders> waitOrders = ordersRepository.findByOrderStatAndOrderRegDtBefore(OrderEnum.WAIT, now.minusMinutes(10));

        for (Orders orders : waitOrders) {
            orders.setOrderStat(OrderEnum.CONFIRM);

            // 관련된 배송들 상태도 변경
            List<Delivery> deliveries = deliveryRepository.findByOrders(orders);
            for (Delivery delivery : deliveries) {
                delivery.setDeliStat(DeliveryEnum.DELIVERY_READY);
                deliveryHistoryRepository.save(
                        DeliveryHistory.builder()
                                .delivery(delivery)
                                .dhStat(DeliveryEnum.DELIVERY_READY)
                                .build()
                );
            }
        }
    }
}
