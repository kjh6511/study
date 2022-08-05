package com.looknlook.looknlook.cart.service;

import com.looknlook.looknlook.cart.domain.request.ReqCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;


    @Test
    void createCart(){

        ReqCart reqCart = new ReqCart();
        reqCart.setStockQua(3);
        reqCart.setStockNo(5L);

        cartService.createCart(6L, reqCart);

    }
}