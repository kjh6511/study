package com.looknlook.looknlook.order.controller;

import com.looknlook.looknlook.Item.domain.response.ResItemCategoryMenuList;
import com.looknlook.looknlook.Item.service.ItemService;
import com.looknlook.looknlook.order.domain.request.ReqOrder;
import com.looknlook.looknlook.order.domain.request.ReqOrderStock;
import com.looknlook.looknlook.order.service.OrderService;
import com.looknlook.looknlook.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public String readOrder(@RequestBody List<ReqOrderStock> orderStocks, Model model)throws Exception{



        return "";
    }

}
