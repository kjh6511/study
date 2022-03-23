package com.masjjim.board.mapper;

import com.masjjim.board.domain.entity.BoardCategory;
import com.masjjim.board.domain.entity.BoardMenu;
import com.masjjim.board.domain.entity.BoardMenuStore;
import com.masjjim.board.domain.response.ResBoardCategory;
import com.masjjim.board.domain.response.ResBoardMenu;
import com.masjjim.store.domain.entity.StoreReply;
import com.masjjim.store.domain.request.ReqStoreMenu;

import java.util.List;

public interface BoardMapper {
    void createBoardCategory(BoardCategory boardCategory);
    void createBoardMenu(BoardMenu boardMenu);
    void createBoardMenuStore(BoardMenuStore boardMenuStore);
    List<ResBoardMenu> readBoardMenuList(Integer borCatNo);
    List<ResBoardCategory> readBoardCategoryList();
}
