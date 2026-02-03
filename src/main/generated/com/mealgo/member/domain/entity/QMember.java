package com.mealgo.member.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 179154521L;

    public static final QMember member = new QMember("member1");

    public final StringPath memAuth = createString("memAuth");

    public final StringPath memId = createString("memId");

    public final StringPath memNm = createString("memNm");

    public final NumberPath<Integer> memNo = createNumber("memNo", Integer.class);

    public final StringPath memPw = createString("memPw");

    public final DateTimePath<java.time.LocalDateTime> memRegDt = createDateTime("memRegDt", java.time.LocalDateTime.class);

    public final EnumPath<com.mealgo.common.code.MemberEnum> memStat = createEnum("memStat", com.mealgo.common.code.MemberEnum.class);

    public final DateTimePath<java.time.LocalDateTime> memUpDt = createDateTime("memUpDt", java.time.LocalDateTime.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

