package com.looknlook.looknlook.member.repository;

import com.looknlook.looknlook.member.domain.ResponseDto.QResMember;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.member.domain.entity.QMember.member;


public class MemberQueryRepositoryImpl implements MemberQueryRepository{

    private final JPAQueryFactory queryFactory;
    public MemberQueryRepositoryImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ResMember> findAllMemberBy() {
    return queryFactory
                .select(new QResMember(member))
                .from(member)
                .fetch();
    }


}
