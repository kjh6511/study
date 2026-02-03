package com.mealgo.orders.service;

import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.cart.repository.CartRepository;
import com.mealgo.common.code.CartEnum;
import com.mealgo.common.code.OrderEnum;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.member.repository.MemberRepository;
import com.mealgo.orders.domain.dto.RequestOrder;
import com.mealgo.orders.domain.dto.ResponseOrder;
import com.mealgo.orders.domain.dto.SearchOrderCondition;
import com.mealgo.orders.domain.entity.Orders;
import com.mealgo.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    @Transactional
    public Integer createOrder(Integer memNo, RequestOrder requestOrder) {
        Member member = memberRepository.findById(memNo).orElseThrow();
        List<Cart> carts = cartRepository.findByCartNoIn(requestOrder.getCartList());
        String orderNum= generateOrderCode();

        System.out.println("carts "+ carts);
        Orders orders = Orders.builder()
                .member(member)
                .orderNum(orderNum)
                .orderAm(requestOrder.getOrderAm())
                .orderMet(requestOrder.getOrderMet())
                .orderShip(requestOrder.getOrderShip())
                .orderDis(requestOrder.getOrderDis())
                .orderMetDis(requestOrder.getOrderMetDis())
                .orderStat(OrderEnum.WAIT) // 기본 상태
                .orderRegDt(LocalDateTime.now())
                .build();

        ordersRepository.save(orders);

        for (Cart cart : carts) {
            cart.setOrders(orders); // 주문 연결
            cart.setCartStat(CartEnum.BUY); // 상태도 '구매'로 변경
        }
        return orders.getOrderNo();
    }

    public String generateOrderCode() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // 20250410
        int countToday = ordersRepository.countByDate(LocalDate.now()); // 예: 5
        String numberPart = String.format("%05d", countToday + 1); // 00006
        return "ORD" + datePart + "-" + numberPart; // ORD20250410-00006
    }

    public ResponseOrder findById(Integer orderNo) {
        return ordersRepository.findWithCartsByOrderNo(orderNo)
                .map(ResponseOrder::from)
                .orElseThrow();
    }

    public Page<ResponseOrder> readOrderList(SearchOrderCondition condition, Pageable pageable) {
        Page<Orders> orders = ordersRepository.search(condition, pageable);
        return orders.map(ResponseOrder::from);
    }

}
