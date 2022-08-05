package com.looknlook.looknlook.Item.controller;

import com.looknlook.looknlook.Item.domain.request.ReqItem;
import com.looknlook.looknlook.Item.domain.request.ReqItemSearch;
import com.looknlook.looknlook.Item.domain.response.ResItem;
import com.looknlook.looknlook.Item.domain.response.ResItemCategoryMenuList;
import com.looknlook.looknlook.Item.domain.response.ResItemDetail;
import com.looknlook.looknlook.Item.domain.response.ResItemWithShop;
import com.looknlook.looknlook.Item.service.ItemService;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import com.looknlook.looknlook.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/list")
    public String readItemList(@RequestBody ReqItemSearch search, Pageable pageable, Model model){
        // /list?page=1&size=10 방식으로 요청 pageable에 자동 바인딩(값이 없으면 page=0, size=20)
        //추후 페이징+검색+최신순+조회순
        Page<ResItemWithShop> itemList = itemService.readItemList(search,pageable);
        model.addAttribute("itemList",itemList);
        return "item/item_list";
    }

    @GetMapping("/{itemNo}")
    public String readItem(@PathVariable("itemNo") Long itemNo, Model model){
        ResItemDetail item = itemService.readItem(itemNo);
        model.addAttribute("item",item);
        return "item/item_detail";
    }
}
