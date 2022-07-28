package com.looknlook.looknlook.shop.controller;

import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.RequestDto.ReqShop;
import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.looknlook.looknlook.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    //등록화면
    @GetMapping("/create")
    public String readCreateShop(Model model, @AuthenticationPrincipal Member getMember){
        ResShop shop = shopService.readMyShop(getMember.getMemNo());
        if(shop != null){ //이미 등록되어 있음
            return "redirect:/shop/myshop";
        }else { //최초 등록
        model.addAttribute("menu", "shop");
        return "shop/shop_create";
        }
    }

    //등록
    @PostMapping("/create")
    @ResponseBody
    public String createShop(@AuthenticationPrincipal Member getMember, @RequestBody ReqShop reqShop){
        String result = shopService.createShop(getMember.getMemNo(),reqShop);
        return result;
    }

    //shop 상세화면
    @GetMapping("/myshop")
    public String readMyShop(@AuthenticationPrincipal Member getMember, Model model){
        ResShop shop = shopService.readMyShop(getMember.getMemNo());
        model.addAttribute("shop",shop);
        return "shop/shop_read";
    }

    //수정
      @GetMapping("/modify/{shopNo}")
      public String readUdateShop(@PathVariable("shopNo") Long shopNo, Model model){

          return "/";
      }

    //수정
    @PostMapping("/modify")
    public String updateShop(@RequestBody ReqShop reqShop){

        return "/";
    }


    //shop 아이템 카테고리별 상세화면
    @GetMapping("/{icNum}")
    public String readshop(@PathVariable("icNum") Long icNum, Model model) {
        model.addAttribute("menu", "shop");
        model.addAttribute("subMenu", icNum);
        model.addAttribute("icNum",icNum);
        System.out.println(icNum);
        return "shop/shop";
    }

}
