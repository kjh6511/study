package com.looknlook.looknlook.board.repository;

import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.board.domain.request.ReqBoardSearch;
import com.looknlook.looknlook.board.domain.response.ResBoardList;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQueryRepository {

    Page<ResBoardList> findAllByBoardTypeWithItemPage(ReqBoardSearch search, Pageable pageable);
}
