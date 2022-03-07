package com.masjjim.store.service;

import com.masjjim.board.domain.entity.BoardMenuStore;
import com.masjjim.board.mapper.BoardMapper;
import com.masjjim.store.domain.entity.Store;
import com.masjjim.store.domain.entity.StoreCategory;
import com.masjjim.store.domain.entity.StoreMenu;
import com.masjjim.store.domain.entity.StoreReply;
import com.masjjim.store.domain.request.ReqStore;
import com.masjjim.store.domain.request.ReqStoreCategory;
import com.masjjim.store.domain.request.ReqStoreMenu;
import com.masjjim.store.domain.request.ReqStoreReply;
import com.masjjim.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreMapper storeMapper;
    private final BoardMapper boardMapper;


    public void createStore(Integer memNo, ReqStore reqStore) {
        Store store = new Store();
        store.setStoName(reqStore.getStoName());
        store.setStoNum(reqStore.getStoNum());
        store.setStoCell(reqStore.getStoCell());
        store.setStoAddr1(reqStore.getStoAddr1());
        store.setStoAddr2(reqStore.getStoAddr2());
        store.setStoInfo(reqStore.getStoInfo());
        store.setStoType(reqStore.getStoType());
        store.setStoStat("004001");//활성
        storeMapper.createStore(store);

        //게시판 분류
        BoardMenuStore boardMenuStore = new BoardMenuStore();
        boardMenuStore.setBorMenuNo(reqStore.getBorMenuNo());
        boardMenuStore.setStoNo(store.getStoNo());
        boardMapper.createBoardMenuStore(boardMenuStore);

    }

    public void createStoreCategory(ReqStoreCategory reqStoreCategory){
        StoreCategory storeCategory = new StoreCategory();
        storeCategory.setStoNo(reqStoreCategory.getStoNo());
        storeCategory.setStoCatName(reqStoreCategory.getStoCatName());
        storeCategory.setStoCatNum(reqStoreCategory.getStoCatNum());
        storeCategory.setStoCatStat("004001");//활성
        storeMapper.createStoreCategory(storeCategory);
    }

    public void createStoreMenu(ReqStoreMenu reqStoreMenu){
        StoreMenu storeMenu = new StoreMenu();
        storeMenu.setStoCatNo(reqStoreMenu.getStoCatNo());
        storeMenu.setStoMenuNum(reqStoreMenu.getStoMenuNum());
        storeMenu.setStoMenuName(reqStoreMenu.getStoMenuName());
        storeMenu.setStoMenuInfo(reqStoreMenu.getStoMenuInfo());
        storeMenu.setStoMenuPrice(reqStoreMenu.getStoMenuPrice());
        storeMenu.setStoMenuStat("004001");//활성
        storeMapper.createStoreMenu(storeMenu);
    }

    public void createStoreReply(Integer memNo, ReqStoreReply reqStoreReply){
        StoreReply storeReply = new StoreReply();
        storeReply.setStoNo(reqStoreReply.getStoNo());
        storeReply.setMemNo(memNo);
        storeReply.setReStoRlyNo(reqStoreReply.getReStoRlyNo());
        storeReply.setStoRlyInfo(reqStoreReply.getStoRlyInfo());
        storeReply.setStoRlyStat("004001");//활성
        storeMapper.createStoreReply(storeReply);
    }
}
