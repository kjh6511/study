package com.mealgo.shop.service;

import com.mealgo.common.code.ShopEnum;
import com.mealgo.common.paging.SearchCondition;
import com.mealgo.shop.domain.dto.RequestShop;
import com.mealgo.shop.domain.dto.ResponseShop;
import com.mealgo.shop.domain.entity.Shop;
import com.mealgo.shop.repository.ShopRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop createShop(RequestShop requestShop){
        Shop shop = Shop.builder()
                .shopNm(requestShop.getShopNm())
                .shopId(requestShop.getShopId())
                .shopInfo(requestShop.getShopInfo())
                .shopTel(requestShop.getShopTel())
                .shopImg(requestShop.getShopImg())
                .shopRegDt(LocalDateTime.now())
                .shopStat(ShopEnum.OPEN)
        .build();
        shopRepository.save(shop);
        return shop;
    }

    public Page<ResponseShop> readShopList(SearchCondition condition, Pageable pageable) {
        return shopRepository.search(condition, pageable)
                .map(ResponseShop::from);
    }

    public ResponseShop readShopDetail(Integer shopNo) {
        return shopRepository.findById(shopNo)
                .map(ResponseShop::from)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found"));
    }

    public void updateShop(RequestShop requestShop){
        Shop shop = Shop.builder()
                .shopNo(requestShop.getShopNo())
                .shopNm(requestShop.getShopNm())
                .shopInfo(requestShop.getShopInfo())
                .shopId(requestShop.getShopId())
                .shopTel(requestShop.getShopTel())
                .shopStat(requestShop.getShopStat())
                .shopImg(requestShop.getShopImg())
                .shopUpDt(LocalDateTime.now())
                .build();
        shopRepository.save(shop);
    }

    public List<ResponseShop> readShopAll() {
        return shopRepository.findAll()
                .stream().map(ResponseShop::from)
                .collect(Collectors.toList());
    }
}
