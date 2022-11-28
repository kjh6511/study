package com.looknlook.looknlook.order.service;

import com.looknlook.looknlook.order.domain.request.ReqOrder;
import com.looknlook.looknlook.order.domain.response.ResOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder() throws Exception {
        ReqOrder reqOrder = new ReqOrder();
        reqOrder.setOrderAddr("서울시 영등포구 여의도동 국회대로");
        reqOrder.setOrderPay("05002");
        reqOrder.setOrderName("test");
        reqOrder.setOrderPhone("010-1234-1234");
        reqOrder.setOrderAddrNum("12345");
        reqOrder.setCartNoArry(new Long[]{6L, 7L});

        orderService.createOrder(reqOrder,6L);
    }

    @Test
    void readOrder()throws Exception{
        ResOrder resOrder = orderService.readOrder(1L);
        System.out.println(resOrder);
    }
}