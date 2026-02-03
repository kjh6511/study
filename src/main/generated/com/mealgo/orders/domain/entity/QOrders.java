package com.mealgo.orders.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 852646713L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final ListPath<com.mealgo.cart.domain.entity.Cart, com.mealgo.cart.domain.entity.QCart> carts = this.<com.mealgo.cart.domain.entity.Cart, com.mealgo.cart.domain.entity.QCart>createList("carts", com.mealgo.cart.domain.entity.Cart.class, com.mealgo.cart.domain.entity.QCart.class, PathInits.DIRECT2);

    public final com.mealgo.member.domain.entity.QMember member;

    public final NumberPath<Integer> orderAm = createNumber("orderAm", Integer.class);

    public final NumberPath<Integer> orderDis = createNumber("orderDis", Integer.class);

    public final EnumPath<com.mealgo.common.code.PayEnum> orderMet = createEnum("orderMet", com.mealgo.common.code.PayEnum.class);

    public final EnumPath<com.mealgo.common.code.SaleEnum> orderMetDis = createEnum("orderMetDis", com.mealgo.common.code.SaleEnum.class);

    public final NumberPath<Integer> orderNo = createNumber("orderNo", Integer.class);

    public final StringPath orderNum = createString("orderNum");

    public final DateTimePath<java.time.LocalDateTime> orderRegDt = createDateTime("orderRegDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> orderShip = createNumber("orderShip", Integer.class);

    public final EnumPath<com.mealgo.common.code.OrderEnum> orderStat = createEnum("orderStat", com.mealgo.common.code.OrderEnum.class);

    public final DateTimePath<java.time.LocalDateTime> orderUpDt = createDateTime("orderUpDt", java.time.LocalDateTime.class);

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.mealgo.member.domain.entity.QMember(forProperty("member")) : null;
    }

}

