package com.masjjim.board.service;

import com.masjjim.board.domain.entity.BoardMenu;
import com.masjjim.board.domain.request.ReqBoardMenu;
import com.masjjim.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void createBoardMenu(ReqBoardMenu reqBoardMenu)throws Exception{
        BoardMenu boardMenu = new BoardMenu();
        boardMenu.setBorCateNo(reqBoardMenu.getBorCateNo());
        boardMenu.setBorMenuName(reqBoardMenu.getBorMenuName());
        boardMenu.setBorMenuStat(reqBoardMenu.getBorMenuStat());
        boardMenu.setBorMenuType("003003");
        boardMenu.setBorMenuNum(reqBoardMenu.getBorMenuNum());
        boardMapper.createBoardMenu(boardMenu);
    }
}
