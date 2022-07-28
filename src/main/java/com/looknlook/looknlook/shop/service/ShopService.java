package com.looknlook.looknlook.shop.service;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.response.ResItem;
import com.looknlook.looknlook.Item.repository.ItemRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.shop.domain.RequestDto.ReqShop;
import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.looknlook.looknlook.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public String createShop(Long memNo, ReqShop reqShop) {
        Shop checkShop = shopRepository.findByMemNo(memNo);
        System.out.println(checkShop);
        if (checkShop != null) {
            return "FAILURE";
        } else {
            Member member = memberRepository.findById(memNo)
                    .orElseThrow(NullPointerException::new);
            Shop shop = Shop.createShop(member, reqShop);
            shopRepository.save(shop);
            return "SUCCESS";
        }
    }

    @Transactional
    public ResShop readMyShop(Long memNo) {
        Shop shop = shopRepository.findByMemNoWithMember(memNo);
        ResShop resShop = new ResShop(shop);
        List<Item> items = itemRepository.findAllByShopNoWithStock(shop.getShopNo());
        List<ResItem> resItems = items.stream().map(ResItem::new).collect(Collectors.toList());
        resShop.setItems(resItems);
        return resShop;
    }

    public Shop readShop(long shopNo) {
        return shopRepository.findById(shopNo)
                .orElseGet(Shop::new);
    }
}
