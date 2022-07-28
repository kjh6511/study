package com.looknlook.looknlook.Item.controller;

import com.looknlook.looknlook.Item.domain.request.ReqItem;
import com.looknlook.looknlook.Item.domain.response.ResItemCategoryMenuList;
import com.looknlook.looknlook.Item.service.ItemService;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import com.looknlook.looknlook.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ShopService shopService;

    @ResponseBody
    @GetMapping("/category")
    public List<ResItemCategoryMenuList> readItemCategoryList()throws Exception{
        List<ResItemCategoryMenuList> categoryList = itemService.readItemCategoryList();
        return categoryList;
    }

    @ResponseBody
    @GetMapping("/category/select/{icNum}")
    public List<ResItemCategoryMenuList> readSelectCategory(@PathVariable("icNum") Long icNum){
        return itemService.readItemCategoryDownList(icNum);
    }

    @GetMapping("/create")
    public String readCreateItem(@AuthenticationPrincipal Member getMember, Model model)throws Exception{
        ResShop shop = shopService.readMyShop(getMember.getMemNo());
        List<ResItemCategoryMenuList> top = itemService.readItemCategoryTopList();
        List<ResItemCategoryMenuList> down = itemService.readItemCategoryDownList(top.get(0).getIcNum());
        model.addAttribute("shop",shop);
        model.addAttribute("top", top);
        model.addAttribute("down", down);
        return "shop/item_create";
    }

    @ResponseBody
    @PostMapping("/create")
    public void createItem(@AuthenticationPrincipal Member getMember, @RequestBody ReqItem resItem)throws Exception{
        itemService.createItemif(getMember.getMemNo(), resItem);
    }
}
