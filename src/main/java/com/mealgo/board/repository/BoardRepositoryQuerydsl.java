package com.mealgo.board.repository;

import com.mealgo.board.domain.dto.SearchBoardCondition;
import com.mealgo.board.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryQuerydsl {
    Page<Board> search (SearchBoardCondition condition, Pageable pageable);
}
