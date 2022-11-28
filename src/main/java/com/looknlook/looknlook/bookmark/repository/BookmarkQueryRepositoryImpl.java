package com.looknlook.looknlook.bookmark.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.entity.QItem;
import com.looknlook.looknlook.Item.domain.request.ReqItemSearch;
import com.looknlook.looknlook.Item.domain.response.QResItemWithShop;
import com.looknlook.looknlook.Item.domain.response.ResItemWithShop;
import com.looknlook.looknlook.Item.repository.ItemQueryRepository;
import com.looknlook.looknlook.bookmark.domain.entity.Bookmark;
import com.looknlook.looknlook.bookmark.domain.entity.QBookmark;
import com.looknlook.looknlook.bookmark.domain.request.ReqBookmarkSearch;
import com.looknlook.looknlook.bookmark.domain.response.QResBookmarkItem;
import com.looknlook.looknlook.bookmark.domain.response.ResBookmarkItem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import nonapi.io.github.classgraph.utils.LogNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.bookmark.domain.entity.QBookmark.bookmark;
import static com.looknlook.looknlook.shop.domain.entity.QShop.shop;
import static org.springframework.util.StringUtils.hasText;


public class BookmarkQueryRepositoryImpl implements BookmarkQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BookmarkQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Page<ResBookmarkItem> findAllByMemNoWithItemPage(Long memNo, ReqBookmarkSearch search, Pageable pageable) {
        List<ResBookmarkItem> contents = queryFactory
                .select(new QResBookmarkItem(bookmark))
                .from(bookmark)
                .leftJoin(bookmark.item, item).fetchJoin()
                .leftJoin(bookmark.shop, shop).fetchJoin()
                .where(memNoEq(memNo)
                        .and(bmTypeEq(search.getBmType())
                                .or(icNumEq(search.getIcNum()))))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Bookmark> countQuery = queryFactory.selectFrom(bookmark)
                .leftJoin(bookmark.item, item).fetchJoin()
                       .leftJoin(bookmark.shop, shop).fetchJoin()
                       .where(memNoEq(memNo)
                               .and(bmTypeEq(search.getBmType())
                                       .or(icNumEq(search.getIcNum()))))
                       .distinct();
        return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.fetchCount());
    }

    private BooleanExpression memNoEq(Long memNo){
        return memNo != null ? bookmark.member.memNo.eq(memNo) : null;
    }

    private BooleanExpression bmTypeEq(String bmType){
        return hasText(bmType) ? bookmark.bmType.contains(bmType) : null;
    }

    private BooleanExpression icNumEq(Long icNum){
        return icNum != null ? bookmark.item.itemCategory.icNum.eq(icNum) : null;
    }
}
