package com.looknlook.looknlook.board.repository;

import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.board.domain.entity.BoardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Long>{

    @Transactional
    @Modifying
    @Query(value = "delete from board_item bi where bi.board_no = :boardNo", nativeQuery = true)
    void deleteByBoardNo(@Param("boardNo") Long boardNo);
}
