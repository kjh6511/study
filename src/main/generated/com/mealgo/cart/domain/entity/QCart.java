package com.mealgo.cart.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = 585491161L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final NumberPath<Integer> cartCnt = createNumber("cartCnt", Integer.class);

    public final NumberPath<Integer> cartNo = createNumber("cartNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> cartRegDt = createDateTime("cartRegDt", java.time.LocalDateTime.class);

    public final EnumPath<com.mealgo.common.code.CartEnum> cartStat = createEnum("cartStat", com.mealgo.common.code.CartEnum.class);

    public final DateTimePath<java.time.LocalDateTime> cartUpDt = createDateTime("cartUpDt", java.time.LocalDateTime.class);

    public final com.mealgo.meal.domain.entity.QMeal meal;

    public final com.mealgo.member.domain.entity.QMember member;

    public final com.mealgo.orders.domain.entity.QOrders orders;

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.meal = inits.isInitialized("meal") ? new com.mealgo.meal.domain.entity.QMeal(forProperty("meal"), inits.get("meal")) : null;
        this.member = inits.isInitialized("member") ? new com.mealgo.member.domain.entity.QMember(forProperty("member")) : null;
        this.orders = inits.isInitialized("orders") ? new com.mealgo.orders.domain.entity.QOrders(forProperty("orders"), inits.get("orders")) : null;
    }

}

