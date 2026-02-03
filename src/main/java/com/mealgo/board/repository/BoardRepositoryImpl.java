package com.mealgo.board.repository;

import com.mealgo.board.domain.dto.SearchBoardCondition;
import com.mealgo.board.domain.entity.Board;
import com.mealgo.board.domain.entity.QBoard;
import com.mealgo.member.domain.entity.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

public class BoardRepositoryImpl implements BoardRepositoryQuerydsl{
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Board> search(SearchBoardCondition condition, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QBoard board = QBoard.board;
        QMember member = QMember.member;

        // 로그인한 회원 조건 추가(내가 쓴글 확인)
        if (condition.getMemNo() != null) {
            builder.and(board.member.memNo.eq(condition.getMemNo()));
        }

        //게시판 종류
        if (condition.getBrdType() != null){
            builder.and(board.brdType.eq(condition.getBrdType()));
        }

        // 키워드 검색
        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(
                    board.brdTitle.containsIgnoreCase(condition.getKeyword())
            );
        }

        List<Board> content = queryFactory
                .selectFrom(board)
                .leftJoin(board.member, member).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.brdNo.desc())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .leftJoin(board.member, member)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
