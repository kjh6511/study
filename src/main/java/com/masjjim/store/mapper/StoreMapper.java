package com.masjjim.store.mapper;

import com.masjjim.store.domain.entity.Store;
import com.masjjim.store.domain.entity.StoreCategory;
import com.masjjim.store.domain.entity.StoreMenu;
import com.masjjim.store.domain.entity.StoreReply;
import com.masjjim.store.domain.response.ResStore;
import com.masjjim.store.domain.response.ResStoreList;

import java.util.List;

public interface StoreMapper {
    void createStore(Store store);
    void createStoreCategory(StoreCategory storeCategory);
    void createStoreMenu(StoreMenu storeMenu);
    void createStoreReply(StoreReply storeReply);
    ResStore readStore(Integer stoNo);
    List<ResStoreList> readStoreList(Integer borMenuNo);
}
