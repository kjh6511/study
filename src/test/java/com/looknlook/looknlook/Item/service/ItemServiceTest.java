package com.looknlook.looknlook.Item.service;

import com.looknlook.looknlook.Item.domain.request.ReqItemSearch;
import com.looknlook.looknlook.Item.domain.response.ResItemWithShop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void readItemList(){
        ReqItemSearch search = new ReqItemSearch();
        search.setIcNum(3L);
        search.setSearch("03");
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<ResItemWithShop> result = itemService.readItemList(search, pageRequest);
        List<ResItemWithShop> list = result.getContent();
        list.forEach(System.out::println);
    }

    @Test
    void readItem(){
        System.out.println(itemService.readItem(5L));
    }
}