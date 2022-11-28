package com.looknlook.looknlook.cart.service;

import com.looknlook.looknlook.cart.domain.request.ReqCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.common.codeEnum.CodeStatus;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private MemberService memberService;

    @Test
    void createCart(){

        ReqCart reqCart = new ReqCart();
        reqCart.setStockQua(3);
        reqCart.setStockNo(6L);

        cartService.createCart(6L, reqCart);

    }

    @Test
    void readCartList(){
        List<ResCart> list = cartService.readCartList(6L);
        for(ResCart cart:list){
            String code = cart.getStockStu();
        }
        System.out.println(list);
    }

    @Test
    void  readCartSelectList(){
        Long[] cartNoList = {3L,4L};
        List<ResCart> cartList = cartService.readCartSelectList(cartNoList);
        System.out.println(cartList);
    }
}