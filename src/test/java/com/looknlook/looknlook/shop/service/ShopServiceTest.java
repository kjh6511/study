package com.looknlook.looknlook.shop.service;

import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


//@ExtendWith(MockitoExtension.class)//단위테스트
@SpringBootTest
class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Test
    void readMyShop()throws Exception{
        ResShop shop = shopService.readMyShop(6L);
        System.out.println(shop);
    }

}