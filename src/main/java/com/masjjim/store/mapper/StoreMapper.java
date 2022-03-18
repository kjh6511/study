package com.masjjim.store.mapper;

import com.masjjim.store.domain.entity.Store;
import com.masjjim.store.domain.entity.StoreCategory;
import com.masjjim.store.domain.entity.StoreMenu;
import com.masjjim.store.domain.entity.StoreReply;
import com.masjjim.store.domain.response.ResStore;

public interface StoreMapper {
    void createStore(Store store);
    void createStoreCategory(StoreCategory storeCategory);
    void createStoreMenu(StoreMenu storeMenu);
    void createStoreReply(StoreReply storeReply);
    ResStore readStore(Integer stoNo);
}
