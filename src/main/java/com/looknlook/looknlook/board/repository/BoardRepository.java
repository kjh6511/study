package com.looknlook.looknlook.board.repository;

import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.cart.repository.CartQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardQueryRepository {

    @Query("SELECT DISTINCT b " +
            "FROM Board b " +
            "LEFT JOIN FETCH b.member m " +
            "LEFT JOIN FETCH b.boardItems bi " +
            "LEFT JOIN FETCH bi.item i " +
            "LEFT JOIN FETCH i.shop s " +
            "WHERE b.boardType = :boardType")
    List<Board> findAllByBoardTypeWithItem(@Param("boardType")String boardType);

    @Query("SELECT DISTINCT b " +
            "FROM Board b " +
            "LEFT JOIN FETCH b.member m " +
            "LEFT JOIN FETCH b.boardItems bi " +
            "LEFT JOIN FETCH bi.item i " +
            "LEFT JOIN FETCH i.shop s " +
            "WHERE b.boardNo = :boardNo")
    Board findByBoardNoWithItem(@Param("boardNo")Long boardNo);
}
