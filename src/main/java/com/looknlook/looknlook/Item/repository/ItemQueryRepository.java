package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.request.ReqItemSearch;
import com.looknlook.looknlook.Item.domain.response.ResItemWithShop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemQueryRepository {
    Item findByIdWithStock(Long itemNo);

    List<Item> findAllByShopNoWithStock(Long shopNo);

    List<Item> readItemAllByIcNumWithShop(Long icNum);

    Page<ResItemWithShop> findItemListSearchPage(ReqItemSearch search, Pageable pageable);

}
