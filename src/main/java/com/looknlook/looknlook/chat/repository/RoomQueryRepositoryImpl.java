package com.looknlook.looknlook.chat.repository;

import com.looknlook.looknlook.cart.domain.response.QResCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.repository.CartQueryRepository;
import com.looknlook.looknlook.chat.domain.entity.ChatMember;
import com.looknlook.looknlook.chat.domain.entity.Room;
import com.looknlook.looknlook.chat.domain.response.QResRoom;
import com.looknlook.looknlook.chat.domain.response.ResChat;
import com.looknlook.looknlook.chat.domain.response.ResChatMember;
import com.looknlook.looknlook.chat.domain.response.ResRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.cart.domain.entity.QCart.cart;
import static com.looknlook.looknlook.chat.domain.entity.QChat.chat;
import static com.looknlook.looknlook.chat.domain.entity.QChatMember.chatMember;
import static com.looknlook.looknlook.chat.domain.entity.QRoom.room;
import static com.looknlook.looknlook.member.domain.entity.QMember.member;


public class RoomQueryRepositoryImpl implements RoomQueryRepository {

    private final JPAQueryFactory queryFactory;

    public RoomQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ResRoom> findAllByMemNoPage(Long memNo, Pageable pageable) {
        List<ResRoom> contents = queryFactory
                .select(new QResRoom(room))
                .from(room)
                .leftJoin(room.chatMembers, chatMember).fetchJoin()
                .where(memNoEq(memNo))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Room> countQuery = queryFactory.selectFrom(room)
                .leftJoin(room.chatMembers, chatMember).fetchJoin()
                .where(memNoEq(memNo))
                .distinct();

        return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.fetchCount());
    }

    @Override
    public ResRoom findByRoomNo(Long roomNo) {
        return queryFactory.select(new QResRoom(room))
                .from(room)
                .where(room.roomNo.eq(roomNo))
                .fetchOne();
    }

    private BooleanExpression memNoEq(Long memNo) {
        return memNo != null ? chatMember.member.memNo.eq(memNo) : null;
    }
}
