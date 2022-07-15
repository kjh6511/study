package com.looknlook.looknlook.member.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 340748706L;

    public static final QMember member = new QMember("member1");

    public final StringPath auth = createString("auth");

    public final StringPath memId = createString("memId");

    public final StringPath memName = createString("memName");

    public final NumberPath<Long> memNo = createNumber("memNo", Long.class);

    public final StringPath memPw = createString("memPw");

    public final StringPath memStu = createString("memStu");

    public final StringPath memType = createString("memType");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

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

