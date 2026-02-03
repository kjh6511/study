package com.mealgo.board.repository;

import com.mealgo.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer>, BoardRepositoryQuerydsl {
}
