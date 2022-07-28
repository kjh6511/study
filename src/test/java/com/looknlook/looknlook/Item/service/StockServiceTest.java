package com.looknlook.looknlook.Item.service;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private ItemRepository itemIfRepository;

    @Test
    void findByIdWithItem()throws Exception{
        Item item = itemIfRepository.findByIdWithStock(2L);
    }

}