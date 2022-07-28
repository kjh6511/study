package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import com.looknlook.looknlook.Item.domain.entity.QItemCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;

public class ItemCategoryQueryRepositoryImpl implements ItemCategoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ItemCategoryQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ItemCategory> findAllWithTotal() {
        QItemCategory parent = new QItemCategory("parent");
        QItemCategory child = new QItemCategory("child");

        return queryFactory
                .selectFrom(parent)
                .distinct()
                .leftJoin(parent.children, child)
                .fetchJoin()
                .where(parent.parent.isNull())
                .orderBy(parent.icNum.asc(), child.icNum.asc())
                .fetch();
    }


    @Override
    public List<ItemCategory> findAllWithDown(Long icNum) {
        return queryFactory
                .selectFrom(new QItemCategory(itemCategory))
                .where(itemCategory.parent.icNum.eq(icNum))
                .orderBy(itemCategory.icNum.asc())
                .fetch();
    }
}
