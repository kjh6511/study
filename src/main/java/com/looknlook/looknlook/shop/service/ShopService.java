package com.looknlook.looknlook.shop.service;

import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.shop.domain.RequestDto.ReqShop;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.looknlook.looknlook.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;

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

    public Shop readMyShop(Long memNo) {
        return shopRepository.findByMemNo(memNo);
    }
}
