package com.mealgo.shop.service;

import com.mealgo.common.code.ShopEnum;
import com.mealgo.shop.domain.dto.RequestShop;
import com.mealgo.shop.domain.entity.Shop;
import com.mealgo.shop.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @Mock
    ShopRepository shopRepository;

    @InjectMocks
    ShopService shopService;

    @Test
    void createShop() {
        // given
        RequestShop requestShop = new RequestShop();
        requestShop.setShopNm("mealGo");
        requestShop.setShopId("s-001");
        requestShop.setShopInfo("밀고 자체 상품");
        requestShop.setShopTel("123-123");

        Shop savedShop = Shop.builder()
                .shopNm(requestShop.getShopNm())
                .shopId(requestShop.getShopId())
                .shopInfo(requestShop.getShopInfo())
                .shopTel(requestShop.getShopTel())
                .shopStat(ShopEnum.OPEN)
                .shopRegDt(LocalDateTime.now())
                .build();

//        Mockito.when(shopRepository.save(Mockito.any(Shop.class)))
//                .thenReturn(savedShop);

        // when
        Shop result = shopService.createShop(requestShop);

        // then
        assertNotNull(result);
        assertEquals("mealGo", result.getShopNm());
        assertEquals(ShopEnum.OPEN, result.getShopStat());
        assertEquals("s-001", result.getShopId());
    }

}