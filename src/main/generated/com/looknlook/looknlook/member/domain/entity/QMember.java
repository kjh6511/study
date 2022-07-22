package com.looknlook.looknlook.member.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 340748706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath auth = createString("auth");

    public final StringPath memId = createString("memId");

    public final StringPath memName = createString("memName");

    public final NumberPath<Long> memNo = createNumber("memNo", Long.class);

    public final StringPath memPw = createString("memPw");

    public final EnumPath<com.looknlook.looknlook.common.codeEnum.CodeStatus> memStu = createEnum("memStu", com.looknlook.looknlook.common.codeEnum.CodeStatus.class);

    public final StringPath memType = createString("memType");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final com.looknlook.looknlook.shop.domain.entity.QShop shop;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new com.looknlook.looknlook.shop.domain.entity.QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

