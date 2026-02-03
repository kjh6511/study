package com.mealgo.shop.controller;

import com.mealgo.common.code.ShopEnum;
import com.mealgo.common.paging.SearchCondition;
import com.mealgo.shop.domain.dto.RequestShop;
import com.mealgo.shop.domain.dto.ResponseShop;
import com.mealgo.shop.domain.entity.Shop;
import com.mealgo.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/new")
    public String showShopFrom(Model model){
        model.addAttribute(new RequestShop());
        return "shop/shopForm";
    }

    @PostMapping()
    public String createShop(RequestShop requestShop){
        shopService.createShop(requestShop);
        return "redirect:/shops";
    }

    @GetMapping()
    public String readShopList(
            @ModelAttribute SearchCondition condition,
            @PageableDefault(size = 5, sort = "shopNo", direction = Sort.Direction.ASC) Pageable pageable,
            Model model
    ){
        Page<ResponseShop> shopPage = shopService.readShopList(condition, pageable);
        model.addAttribute("shopPage", shopPage);
        model.addAttribute("condition", condition);
        return "shop/shop";
    }

    @GetMapping("/{shopNo}")
    public String readShopDetail(@PathVariable("shopNo") Integer shopNo, Model model){
        ResponseShop responseShop = shopService.readShopDetail(shopNo);
        model.addAttribute("shop", responseShop);
        model.addAttribute("shopStatusList", ShopEnum.values());
        return"shop/shopDetail";
    }

    @PostMapping("/edit")
    @ResponseBody//새로고침 이슈로 ajax방식 사용
    public ResponseEntity<ResponseShop> updateShop(@RequestBody RequestShop requestShop) {
        shopService.updateShop(requestShop);
        ResponseShop updated = shopService.readShopDetail(requestShop.getShopNo());
        return ResponseEntity.ok(updated); // JSON 응답
    }
}
