package com.mealgo.board.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1392420505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath brdContent = createString("brdContent");

    public final NumberPath<Integer> brdNo = createNumber("brdNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> brdRegDt = createDateTime("brdRegDt", java.time.LocalDateTime.class);

    public final EnumPath<com.mealgo.common.code.BoardEnum> brdStat = createEnum("brdStat", com.mealgo.common.code.BoardEnum.class);

    public final StringPath brdTitle = createString("brdTitle");

    public final EnumPath<com.mealgo.common.code.BoardTypeEnum> brdType = createEnum("brdType", com.mealgo.common.code.BoardTypeEnum.class);

    public final DateTimePath<java.time.LocalDateTime> brdUpDt = createDateTime("brdUpDt", java.time.LocalDateTime.class);

    public final com.mealgo.member.domain.entity.QMember member;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.mealgo.member.domain.entity.QMember(forProperty("member")) : null;
    }

}

