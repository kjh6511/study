package com.looknlook.looknlook.order.service;

import com.looknlook.looknlook.Item.repository.StockRepository;
import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.cart.repository.CartRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.order.domain.entity.Orders;
import com.looknlook.looknlook.order.domain.entity.OrderStock;
import com.looknlook.looknlook.order.domain.request.ReqOrder;
import com.looknlook.looknlook.order.domain.response.ResOrder;
import com.looknlook.looknlook.order.repository.OrderRepository;
import com.looknlook.looknlook.order.repository.OrderStockRepository;
import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStockRepository orderStockRepository;
    private final MemberRepository memberRepository;
    private final StockRepository stockRepository;
    private final CartRepository cartRepository;

    @Transactional
    public void createOrder(ReqOrder reqOrder, Long memNo) throws Exception {
        Member member = memberRepository.findById(memNo).orElseThrow(() -> new NullPointerException("member null"));

        Orders orders = Orders.builder()
                .orderDt(LocalDateTime.now())
                .orderStu("03001")
                .orderAddr(reqOrder.getOrderAddr())
                .orderName(reqOrder.getOrderName())
                .orderPhone(reqOrder.getOrderPhone())
                .orderAddrNum(reqOrder.getOrderAddrNum())
                .orderPay(reqOrder.getOrderPay())
                .payMoney(0)
                .member(member)
                .build();
        orderRepository.save(orders);

        int totalMoney = 0;
        for(Long cartNo: reqOrder.getCartNoArry()){
            Cart cart = cartRepository.findById(cartNo).orElseThrow(() -> new Exception("cartNo null"));
            totalMoney += cart.getStock().getItem().getItemAmt() * cart.getStockQua();

            //재고
            OrderStock orderStock = OrderStock.builder()
                    .orderStockStu("03001")
                    .orderStockQua(cart.getStockQua())
                    .orders(orders)
                    .stock(cart.getStock())
                    .build();
            orderStockRepository.save(orderStock);

            // 완료 삭제
            cartRepository.delete(cart);
        }

        //가격 udpate
        orders.setPayMoney(totalMoney);
        orderRepository.save(orders);

    }

    public ResOrder readOrder(Long orderNo) {
        Orders orders = orderRepository.findByOrderNo(orderNo);
        ResOrder resOrder = new ResOrder(orders);
        return resOrder;
    }
}
