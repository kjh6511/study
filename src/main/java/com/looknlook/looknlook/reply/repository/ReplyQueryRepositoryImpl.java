package com.looknlook.looknlook.reply.repository;

import com.looknlook.looknlook.order.repository.OrderQueryRepository;
import com.looknlook.looknlook.reply.domain.entity.QReply;
import com.looknlook.looknlook.reply.domain.entity.Reply;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.member.domain.entity.QMember.member;
import static com.looknlook.looknlook.order.domain.entity.QOrderStock.orderStock;
import static com.looknlook.looknlook.reply.domain.entity.QReply.reply;

public class ReplyQueryRepositoryImpl implements ReplyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ReplyQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Reply> findAllByItemNo(Long itemNo) {
        return queryFactory
                .select(new QReply(reply))
                .from(reply)
                .leftJoin(reply.member, member).fetchJoin()
                .leftJoin(reply.item, item).fetchJoin()
                .leftJoin(itemCategory).on(reply.item.itemCategory.icNum.eq(itemCategory.icNum))
                .leftJoin(reply.orderStock, orderStock).fetchJoin()
                .leftJoin(reply.orderStock.stock, stock)
                .where(reply.item.itemNo.eq(itemNo))
                .distinct()
                .fetch();
    }

    @Override
    public List<Reply> findAllByMemNo(Long memNo) {
        return queryFactory.select(new QReply(reply))
                .from(reply)
                .leftJoin(reply.member, member).fetchJoin()
                .leftJoin(reply.item, item).fetchJoin()
                .leftJoin(itemCategory).on(reply.item.itemCategory.icNum.eq(itemCategory.icNum))
                .leftJoin(reply.orderStock, orderStock).fetchJoin()
                .leftJoin(reply.orderStock.stock, stock)
                .where(reply.member.memNo.eq(memNo))
                .distinct()
                .fetch();
    }
}
