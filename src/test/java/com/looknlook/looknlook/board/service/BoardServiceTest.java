package com.looknlook.looknlook.board.service;

import com.looknlook.looknlook.board.domain.request.ReqBoard;
import com.looknlook.looknlook.board.domain.request.ReqBoardSearch;
import com.looknlook.looknlook.board.domain.request.ReqBoardUpdate;
import com.looknlook.looknlook.board.domain.response.ResBoardList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void createBoard() throws Exception {
        ReqBoard reqBoard = new ReqBoard();
        reqBoard.setBoardNm("게시판 등록 테스트04");
        reqBoard.setBoardType("07001");
        reqBoard.setBoardText("게시판 등록 테스트 중 입니다.!!!!!");
        reqBoard.setItemNoList(new Long[] {2L,4L});

        boardService.createBoard(6L, reqBoard);

    }

    @Test
    void readBoardList()throws Exception{
        boardService.readBoardList("07001").forEach(System.out::println);
    }

    @Test
    void readBoard()throws Exception{
        System.out.println(boardService.readBoard(3L));
    }

    @Test
    void updateBoard()throws Exception{
        ReqBoardUpdate reqBoardUpdate = new ReqBoardUpdate();
        reqBoardUpdate.setBoardNo(1L);
        reqBoardUpdate.setBoardNm("게시판 수정 테스트!!!!0101");
        reqBoardUpdate.setBoardStu("01001");
        reqBoardUpdate.setBoardType("07001");
        reqBoardUpdate.setBoardText("수정테스트@@@@@@@@@@@@@@@@@");
        reqBoardUpdate.setItemNoList(new Long[] {3L,4L,5L});
        boardService.updateBoard(reqBoardUpdate);
    }

    @Test
    void readBoardListPage()throws Exception{
        ReqBoardSearch reqBoardSearch = new ReqBoardSearch();
        reqBoardSearch.setBoardType("07001");
        reqBoardSearch.setSearch("");
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ResBoardList> lists = boardService.readBoardListPage(reqBoardSearch,pageRequest);
        System.out.println(lists.getTotalPages()); //총페이지수
        System.out.println(lists.getNumber()); //현재 페이지 위치
        System.out.println(lists.getSize()); //페이지사이즈
        System.out.println(lists.getSort());
        System.out.println(lists.getPageable());
        System.out.println(lists.getTotalElements()); //검색된 총 수
        System.out.println(lists.getNumberOfElements()); //검색된 총 수

        List<ResBoardList> result = lists.getContent();
        result.forEach(System.out::println);

    }
}