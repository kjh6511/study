package com.looknlook.looknlook.board.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.board.domain.entity.QBoard;
import com.looknlook.looknlook.board.domain.request.ReqBoardSearch;
import com.looknlook.looknlook.board.domain.response.QResBoardList;
import com.looknlook.looknlook.board.domain.response.ResBoardList;
import com.looknlook.looknlook.cart.domain.response.QResCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.repository.CartQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.board.domain.entity.QBoard.board;
import static com.looknlook.looknlook.board.domain.entity.QBoardItem.boardItem;
import static com.looknlook.looknlook.cart.domain.entity.QCart.cart;
import static com.looknlook.looknlook.member.domain.entity.QMember.member;
import static com.looknlook.looknlook.shop.domain.entity.QShop.shop;
import static org.springframework.util.StringUtils.hasText;


public class BoardQueryRepositoryImpl implements BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BoardQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ResBoardList> findAllByBoardTypeWithItemPage(ReqBoardSearch search, Pageable pageable) {
       List<ResBoardList> contents = queryFactory
               .select(new QResBoardList(board))
               .from(board)
               .leftJoin(board.member, member).fetchJoin()
               .where(boardTypeEq(search.getBoardType())
                       .and(boardNmEq(search.getSearch())
//                               .or(memNmEq(search.getSearch()))
                       ))
               .distinct()
               .offset(pageable.getOffset())
               .limit(pageable.getPageSize())
               .fetch();
        JPAQuery<Board> countQuery = queryFactory.selectFrom(board)
                .leftJoin(board.member, member).fetchJoin()
                .where(boardTypeEq(search.getBoardType())
                        .and(boardNmEq(search.getSearch())
//                                .or(memNmEq(search.getSearch()))
                        ))
                .distinct();
        return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.fetchCount());
    }

    private BooleanExpression boardTypeEq(String boardType){
        return boardType != null ? board.boardType.eq(boardType) : null;
    }

    private BooleanExpression boardNmEq(String boardNm) {
            return hasText(boardNm) ? board.boardNm.contains(boardNm) : null;
    }

    private BooleanExpression memNmEq(String memNm) {
            return hasText(memNm) ? board.member.memName.contains(memNm) : null;
    }
}
