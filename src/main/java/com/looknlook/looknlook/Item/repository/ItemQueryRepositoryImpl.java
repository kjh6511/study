package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.entity.QItem;
import com.looknlook.looknlook.Item.domain.request.ReqItemSearch;
import com.looknlook.looknlook.Item.domain.response.QResItemWithShop;
import com.looknlook.looknlook.Item.domain.response.ResItemWithShop;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.shop.domain.entity.QShop.shop;
import static org.springframework.util.StringUtils.hasText;


public class ItemQueryRepositoryImpl implements ItemQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ItemQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Item findByIdWithStock(Long itemNo) {
        return queryFactory
                .selectFrom(item)
                .leftJoin(item.stocks, stock).fetchJoin()
                .leftJoin(item.itemCategory, itemCategory).fetchJoin()
                .leftJoin(item.shop, shop).fetchJoin()
                .where(item.itemNo.eq(itemNo))
                .fetchOne();
    }

    @Override
    public List<Item> findAllByShopNoWithStock(Long shopNo) {
        return queryFactory
                .select(new QItem(item))
                .from(item)
                .leftJoin(item.itemCategory, itemCategory).fetchJoin()
                .leftJoin(item.stocks, stock).fetchJoin()
                .where(item.shop.shopNo.eq(shopNo))
                .distinct()
                .fetch();
    }

    @Override
    public List<Item> readItemAllByIcNumWithShop(Long icNum) {
        return queryFactory
                .select(new QItem(item))
                .from(item)
                .leftJoin(item.itemCategory, itemCategory).fetchJoin()
                .leftJoin(item.shop, shop).fetchJoin()
                .where(item.itemCategory.icNum.eq(icNum))
                .distinct()
                .fetch();
    }

    @Override
    public Page<ResItemWithShop> findItemListSearchPage(ReqItemSearch search, Pageable pageable) {
        List<ResItemWithShop> contents = queryFactory
                .select(new QResItemWithShop(item))
                .from(item)
                .leftJoin(item.itemCategory, itemCategory).fetchJoin()
                .leftJoin(item.shop, shop).fetchJoin()
                .where(icNumEq(search.getIcNum())
                        .and(itemNmEq(search.getSearch())
                                .or(shopNmEq(search.getSearch())))
                )
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        //total을 직접 검색
        JPAQuery<Item> countQuery = queryFactory.selectFrom(item)
                .leftJoin(item.itemCategory, itemCategory).fetchJoin()
                .leftJoin(item.shop, shop).fetchJoin()
                .where(icNumEq(search.getIcNum())
                        .and(itemNmEq(search.getSearch())
                                .or(shopNmEq(search.getSearch())))
                )
                .distinct();
        return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.fetchCount());
    }

    private BooleanExpression icNumEq(Long icNum) {
        return icNum != null ? item.itemCategory.icNum.eq(icNum) : null;
    }

    private BooleanExpression itemNmEq(String itemNm) {
        return hasText(itemNm) ? item.itemNm.contains(itemNm) : null;
    }

    private BooleanExpression shopNmEq(String shopNm) {
        return hasText(shopNm) ? item.shop.shopNm.contains(shopNm) : null;
    }


}
