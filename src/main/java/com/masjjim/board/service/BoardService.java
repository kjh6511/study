package com.masjjim.board.service;

import com.masjjim.board.domain.entity.BoardMenu;
import com.masjjim.board.domain.request.ReqBoardMenu;
import com.masjjim.board.domain.response.ResBoardCategory;
import com.masjjim.board.domain.response.ResBoardMenu;
import com.masjjim.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void createBoardMenu(ReqBoardMenu reqBoardMenu)throws Exception{
        BoardMenu boardMenu = new BoardMenu();
        boardMenu.setBorCatNo(reqBoardMenu.getBorCatNo());
        boardMenu.setBorMenuName(reqBoardMenu.getBorMenuName());
        boardMenu.setBorMenuStat(reqBoardMenu.getBorMenuStat());
        boardMenu.setBorMenuType("003003");
        boardMenu.setBorMenuNum(reqBoardMenu.getBorMenuNum());
        boardMapper.createBoardMenu(boardMenu);
    }

    public List<ResBoardMenu> readBoardMenuList(Integer borCatNo)throws Exception {
        return boardMapper.readBoardMenuList(borCatNo);
    }

    public List<ResBoardCategory> readBoardCategoryList()throws Exception {
        return boardMapper.readBoardCategoryList();
    }
}
