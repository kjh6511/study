package com.mealgo.board.service;

import com.mealgo.board.domain.dto.RequestBoard;
import com.mealgo.board.domain.dto.ResponseBoard;
import com.mealgo.board.domain.dto.SearchBoardCondition;
import com.mealgo.board.domain.entity.Board;
import com.mealgo.board.repository.BoardRepository;
import com.mealgo.common.code.BoardEnum;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.member.repository.MemberRepository;
import com.mealgo.orders.domain.dto.ResponseOrder;
import com.mealgo.orders.domain.dto.SearchOrderCondition;
import com.mealgo.orders.domain.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void saveBoard(RequestBoard dto, Member writer) {
        Member member = memberRepository.findById(writer.getMemNo())
                .orElseThrow(() -> new RuntimeException("회원 없음"));
        Board board =Board.builder()
                .brdType(dto.getBrdType())
                .brdStat(BoardEnum.ACTIVE)
                .brdTitle(dto.getBrdTitle())
                .brdContent(dto.getBrdContent())
                .member(member)
                .brdUpDt(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }

    public Page<ResponseBoard> readBoardList(SearchBoardCondition condition, Pageable pageable) {
        Page<Board> board = boardRepository.search(condition, pageable);
        return board.map(ResponseBoard::from);
    }
}
