package com.masjjim.board.mapper;

import com.masjjim.board.domain.entity.BoardCategory;
import com.masjjim.board.domain.entity.BoardMenu;
import com.masjjim.board.domain.entity.BoardMenuStore;

public interface BoardMapper {
    void createBoardCategory(BoardCategory boardCategory);
    void createBoardMenu(BoardMenu boardMenu);
    void createBoardMenuStore(BoardMenuStore boardMenuStore);
}
