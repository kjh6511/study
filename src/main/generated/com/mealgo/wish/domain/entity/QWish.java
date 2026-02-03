package com.mealgo.wish.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWish is a Querydsl query type for Wish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWish extends EntityPathBase<Wish> {

    private static final long serialVersionUID = 1409273721L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWish wish = new QWish("wish");

    public final com.mealgo.meal.domain.entity.QMeal meal;

    public final com.mealgo.member.domain.entity.QMember member;

    public final NumberPath<Integer> wishNo = createNumber("wishNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> wishRegDt = createDateTime("wishRegDt", java.time.LocalDateTime.class);

    public QWish(String variable) {
        this(Wish.class, forVariable(variable), INITS);
    }

    public QWish(Path<? extends Wish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWish(PathMetadata metadata, PathInits inits) {
        this(Wish.class, metadata, inits);
    }

    public QWish(Class<? extends Wish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.meal = inits.isInitialized("meal") ? new com.mealgo.meal.domain.entity.QMeal(forProperty("meal"), inits.get("meal")) : null;
        this.member = inits.isInitialized("member") ? new com.mealgo.member.domain.entity.QMember(forProperty("member")) : null;
    }

}

