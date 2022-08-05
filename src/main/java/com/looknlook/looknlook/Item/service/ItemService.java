package com.looknlook.looknlook.Item.service;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import com.looknlook.looknlook.Item.domain.request.ReqItem;
import com.looknlook.looknlook.Item.domain.request.ReqItemSearch;
import com.looknlook.looknlook.Item.domain.request.ReqStock;
import com.looknlook.looknlook.Item.domain.response.ResItemCategoryMenuList;
import com.looknlook.looknlook.Item.domain.response.ResItemDetail;
import com.looknlook.looknlook.Item.domain.response.ResItemWithShop;
import com.looknlook.looknlook.Item.repository.ItemCategoryRepository;
import com.looknlook.looknlook.Item.repository.ItemRepository;
import com.looknlook.looknlook.Item.repository.StockRepository;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.looknlook.looknlook.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;
    private final ShopRepository shopRepository;

    public List<ResItemCategoryMenuList> readItemCategoryList() {
        List<ItemCategory> categoryList = itemCategoryRepository.findAllWithTotal();
        List<ResItemCategoryMenuList> menuList = categoryList
                                                .stream()
                                                .map(ResItemCategoryMenuList::new)
                                                .collect(Collectors.toList());
        return menuList;
    }

    public List<ResItemCategoryMenuList> readItemCategoryTopList() {
        List<ItemCategory> categoryList = itemCategoryRepository.findAllWithTop();
        return categoryList
                .stream().map(ResItemCategoryMenuList::new)
                .collect(Collectors.toList());
    }

    public List<ResItemCategoryMenuList> readItemCategoryDownList(Long icNum) {
        List<ItemCategory> categoryList = itemCategoryRepository.findAllWithDown(icNum);
        return categoryList
                .stream().map(ResItemCategoryMenuList::new)
                .collect(Collectors.toList());
    }


    public void createItemif(Long memNo, ReqItem reqItem) {
        Shop shop = shopRepository.findById(reqItem.getShopNo()).get();
        ItemCategory itemCategory = itemCategoryRepository.findById(reqItem.getIcNum()).get();
        Item item = Item.builder()
                .itemNm(reqItem.getItemNm())
                .itemInfo(reqItem.getItemInfo())
                .itemAmt(reqItem.getItemAmt())
                .itemStu("01001")
                .shop(shop)
                .itemCategory(itemCategory)
                .build();
        Item creatItem = itemRepository.save(item);

        //item
        for(ReqStock reqStock : reqItem.getStocks()){
            Stock stock = Stock.builder()
                    .item(creatItem)
                    .stockNm(reqStock.getStockNm())
                    .stockSize(reqStock.getStockSize())
                    .stockQtq(reqStock.getStockQtq())
                    .stockStu("01001")
                    .build();
            stockRepository.save(stock);
        }
    }

    public Page<ResItemWithShop> readItemList(ReqItemSearch search, Pageable pageable) {
        return itemRepository.findItemListSearchPage(search,pageable);
    }

    public ResItemDetail readItem(Long itemNo) {
        Item item = itemRepository.findByIdWithStock(itemNo);
        ResItemDetail itemDetail = new ResItemDetail(item);
        return itemDetail;
    }
}
